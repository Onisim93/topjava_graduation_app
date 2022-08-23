package ru.topjava.graduation_app.web.controllers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.graduation_app.service.RestaurantService;
import ru.topjava.graduation_app.service.VoteService;
import ru.topjava.graduation_app.to.RestaurantTo;
import ru.topjava.graduation_app.util.RestaurantUtil;

import java.util.List;

import static ru.topjava.graduation_app.web.controllers.user.RestaurantController.REST_URL;

@RestController
@Slf4j
@RequestMapping(REST_URL)
public class RestaurantController {
    static final String REST_URL = "api/restaurants";
    @Autowired
    private RestaurantService service;
    @Autowired
    private VoteService voteService;

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("get all");
        return RestaurantUtil.getTos(service.getAllWithMeals(), voteService.getAllForToday());
    }

    @GetMapping("{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get with id = {}", id);
        return RestaurantUtil.getTo(service.get(id), voteService.getAmountVotesForToday(id));
    }


}
