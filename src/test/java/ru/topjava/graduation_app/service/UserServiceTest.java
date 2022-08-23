package ru.topjava.graduation_app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.graduation_app.entity.User;
import ru.topjava.graduation_app.exception.EntityNotFoundException;


class UserServiceTest  extends AbstractServiceTest{
    @Autowired
    private UserService service;

    @Test
    void doSomething() {
        User u = service.get(100001);
        System.out.println(u);
    }

    @Test
    void getAll() {
        System.out.println(service.getAll());
    }

    @Test
    void delete() {
        service.delete(100000);
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.delete(100000));
    }
}