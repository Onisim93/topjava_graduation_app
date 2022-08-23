package ru.topjava.graduation_app.web.controllers.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation_app.entity.Restaurant;
import ru.topjava.graduation_app.service.RestaurantService;

import java.net.URI;
import java.util.List;

import static ru.topjava.graduation_app.web.controllers.admin.AdminRestaurantController.REST_URL;

@RestController
@Slf4j
@RequestMapping(REST_URL)
public class AdminRestaurantController {
    static final String REST_URL = "/api/admin/restaurants";
    @Autowired
    private RestaurantService service;

    @PostMapping()
    public ResponseEntity<Restaurant> save(@RequestBody Restaurant restaurant) {
        log.info("save {}", restaurant);
        Restaurant newRest = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newRest.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newRest);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all");
        return service.getAll();
    }

    @GetMapping("{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get with id = {}", id);
        return service.get(id);
    }

    @PatchMapping("{id}")
    public void update(@PathVariable int id, @RequestBody Restaurant restaurant) {
        log.info("update with id = {}", id);
        service.update(restaurant, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete with id = {}",id);
        service.delete(id);
    }
}
