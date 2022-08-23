package ru.topjava.graduation_app.web.controllers.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.topjava.graduation_app.entity.Vote;
import ru.topjava.graduation_app.service.VoteService;

import java.time.LocalDate;

import static ru.topjava.graduation_app.web.controllers.admin.AdminVoteController.REST_URL;

@RestController
@Slf4j
@RequestMapping(REST_URL)
public class AdminVoteController {
    static final String REST_URL = "/api/admin/votes";
    @Autowired
    VoteService service;

    @GetMapping("/by-date")
    public Iterable<Vote> getAllForRestaurantAndDate(@RequestParam int restaurantId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get all with restaurantId = {} and date = {}", restaurantId, date);
        return service.getAllByRestaurantIdAndDate(restaurantId, date);
    }

}
