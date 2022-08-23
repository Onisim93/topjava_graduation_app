package ru.topjava.graduation_app.web.controllers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation_app.AuthorizedUser;
import ru.topjava.graduation_app.entity.Vote;
import ru.topjava.graduation_app.service.VoteService;

import java.net.URI;
import java.time.LocalDate;

import static ru.topjava.graduation_app.web.controllers.user.VoteController.REST_URL;

@RestController
@Slf4j
@RequestMapping(REST_URL)
public class VoteController {
    static final String REST_URL = "/api/votes";
    @Autowired
    private VoteService service;

    @PostMapping
    public ResponseEntity<Vote> create(@RequestParam int restaurantId, @AuthenticationPrincipal AuthorizedUser user) {
        Vote vote = service.save(restaurantId, user.id());
        log.info("create {}", vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(vote.id()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId, @AuthenticationPrincipal AuthorizedUser user) {
        log.info("update");
        service.update(restaurantId, user.id());
    }

    @GetMapping("/by-date")
    public Vote get(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @AuthenticationPrincipal AuthorizedUser user) {
        log.info("get for date = {}", date);
        return service.get(user.id(), date);
    }
}
