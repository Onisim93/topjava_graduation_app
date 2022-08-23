package ru.topjava.graduation_app.web.controllers.admin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation_app.entity.Meal;
import ru.topjava.graduation_app.service.MealService;

import java.net.URI;
import java.util.List;

import static ru.topjava.graduation_app.web.controllers.admin.AdminMealController.REST_URL;

@RestController
@Slf4j
@RequestMapping(REST_URL)
public class AdminMealController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/meals";
    @Autowired
    MealService service;

    @GetMapping
    public List<Meal> getAll(@PathVariable int restaurantId) {
        log.info("get all with restaurant id = {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @PostMapping
    public ResponseEntity<Meal> create(@PathVariable int restaurantId, @RequestBody Meal meal) {
        log.info("save {} with restaurant id = {}", meal, restaurantId);
        Meal newMeal = service.save(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/admin/restaurants/"+ restaurantId + "/meals" + "/{id}")
                .buildAndExpand(newMeal.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newMeal);
    }

    @GetMapping("{id}")
    public Meal get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get with id = {} and restaurant id = {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete with id = {} and restaurant id = {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    @PutMapping("{id}")
    public void update(@PathVariable int restaurantId, @RequestBody Meal meal) {
        log.info("update {} with restaurant id = {}", meal, restaurantId);
        service.update(meal, restaurantId);
    }

}
