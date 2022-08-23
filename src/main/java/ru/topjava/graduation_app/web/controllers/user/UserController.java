package ru.topjava.graduation_app.web.controllers.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation_app.AuthorizedUser;
import ru.topjava.graduation_app.entity.User;
import ru.topjava.graduation_app.service.UserService;

import javax.validation.Valid;
import java.net.URI;

import static ru.topjava.graduation_app.web.controllers.user.UserController.REST_URL;

@RestController
@RequestMapping(REST_URL)
@Slf4j
public class UserController {
    static final String REST_URL = "api/profile";

    @Autowired
    UserService service;

    @GetMapping
    public User get(@AuthenticationPrincipal AuthorizedUser user) {
        log.info("get with id = {}", user.id());
        return user.getUser();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser user) {
        log.info("delete with id = {}", user.id());
        service.delete(user.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        log.info("register {}", user);
        User u = service.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(u);
    }
}
