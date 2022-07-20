package ru.topjava.graduation_app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void doSomething() {
        logger.info("test logger output in UserService");
    }
}
