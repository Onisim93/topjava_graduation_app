package ru.topjava.graduation_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation_app.entity.Restaurant;
import ru.topjava.graduation_app.repository.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.topjava.graduation_app.util.validation.ValidationUtil.checkNotFoundWithId;


@Service
@Transactional(readOnly = true)
public class RestaurantService {
    private CrudRestaurantRepository repository;

    @Autowired
    public RestaurantService(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "restaurant", key = "#id")
    public Restaurant get(int id) {
       return checkNotFoundWithId(repository.getWithMeals(id, LocalDate.now()), id);
    }


    @Transactional
    @Caching(evict = {
            @CacheEvict(allEntries = true, cacheNames = "restaurants"),
            @CacheEvict(allEntries = true, cacheNames = "restaurantsWithMeals"),
            @CacheEvict(key = "#restaurantId", cacheNames = "restaurant")
    }
    )
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(allEntries = true, cacheNames = "restaurants"),
            @CacheEvict(allEntries = true, cacheNames = "restaurantsWithMeals"),
            @CacheEvict(key = "#restaurantId", cacheNames = "restaurant")
    })
    public void update(Restaurant restaurant, int restaurantId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
        repository.save(restaurant);
    }

    @Caching(evict = {
            @CacheEvict(allEntries = true, cacheNames = "restaurants"),
            @CacheEvict(allEntries = true, cacheNames = "restaurantsWithMeals"),
            @CacheEvict(key = "#id", cacheNames = "restaurant")
    })
    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Cacheable(value = "restaurantsWithMeals")
    public List<Restaurant> getAllWithMeals() {
        return repository.getAllWithMeals(LocalDate.now());
    }




}
