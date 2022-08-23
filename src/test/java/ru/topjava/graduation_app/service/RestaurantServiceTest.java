package ru.topjava.graduation_app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.topjava.graduation_app.entity.Restaurant;

import java.util.List;


class RestaurantServiceTest extends AbstractServiceTest{
    @Autowired
    ApplicationContext appContext;
    @Autowired
    private RestaurantService service;


    @Test
    void getWithVotes() {
        //Assertions.assertThrows(VoiceAlreadyExistException.class, () -> service.saveVote(100003));
        List<Restaurant> list1 = service.getAll();

        list1.forEach(System.out::println);

    }
}