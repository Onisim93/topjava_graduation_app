package ru.topjava.graduation_app.exception;

public class VotingTimeIsUpException extends RuntimeException{
    public VotingTimeIsUpException(String message) {
        super(message);
    }
}
