package ru.topjava.graduation_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation_app.entity.Meal;
import ru.topjava.graduation_app.exception.EntityNotFoundException;
import ru.topjava.graduation_app.repository.CrudMealRepository;
import ru.topjava.graduation_app.repository.CrudRestaurantRepository;

import java.util.List;

import static ru.topjava.graduation_app.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class MealService {
    private CrudMealRepository mealRepository;
    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    public MealService(CrudMealRepository mealRepository, CrudRestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @CacheEvict(cacheNames = "restaurant", key = "#restaurantId")
    public Meal save(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        meal.setRestaurant(restaurantRepository.getReferenceById(restaurantId));

        return mealRepository.save(meal);
    }

    @Transactional
    @CacheEvict(cacheNames = "restaurant", key = "#restaurantId")
    public void update(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        if (!meal.isNew() && get(meal.id(), restaurantId) == null) {
            throw new EntityNotFoundException("Not found entity with " + meal.id());
        }
        meal.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        checkNotFoundWithId(mealRepository.save(meal), meal.id());
    }

    public Meal get(int id, int restaurantId) {
        return checkNotFoundWithId(mealRepository.getByIdAndRestaurantId(id,restaurantId), id);
    }

    @Transactional
    @CacheEvict(cacheNames = "restaurant", key = "#restaurantId")
    public void delete(int id, int restaurantId) {
        mealRepository.delete(id, restaurantId);
    }

    public List<Meal> getAll(int restaurantId) {
        return mealRepository.getAllByRestaurantIdOrderByName(restaurantId);
    }
}
