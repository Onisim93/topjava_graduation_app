package ru.topjava.graduation_app.web.controllers.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation_app.entity.User;
import ru.topjava.graduation_app.service.UserService;

import java.net.URI;
import java.util.List;

import static ru.topjava.graduation_app.web.controllers.admin.AdminUserController.REST_URL;

@RestController
@Slf4j
@RequestMapping(REST_URL)
public class AdminUserController {
    static final String REST_URL = "/api/admin/users";
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll() {
        log.info("get all");
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("save {}", user);
        User newUser = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newUser);
    }

    @GetMapping("{id}")
    public User get(@PathVariable int id) {
        log.info("get with id = {}", id);
        return service.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete with id = {}", id);
        service.delete(id);
    }

    @PatchMapping("{id}")
    public void update(@PathVariable int id, @RequestBody User user) {
        log.info("update {}", user);
        service.update(user);
    }

}
