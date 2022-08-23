package ru.topjava.graduation_app.web.validation;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.topjava.graduation_app.exception.EntityNotFoundException;
import ru.topjava.graduation_app.exception.VotingTimeIsUpException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
    private static final String EXCEPTION_DUPLICATE_EMAIL = "User with this email already exists";
    private static final String EXCEPTION_DUPLICATE_RESTAURANT = "Restaurant with this name already exists";
    private static final String EXCEPTION_DUPLICATE_MEAL = "Meal with this name in this restaurant already exists today";
    private static final String EXCEPTION_DUPLICATE_VOTE = "Your today vote is already created";

    Map<String, String> CONSTRAINT_MAP = Map.of("users_unique_email_idx", EXCEPTION_DUPLICATE_EMAIL,
                                                "restaurants_unique_name_idx", EXCEPTION_DUPLICATE_RESTAURANT,
            "meals_unique_restaurant_name_date_idx", EXCEPTION_DUPLICATE_MEAL,
            "votes_unique_user_id_local_date_idx", EXCEPTION_DUPLICATE_VOTE);


    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }


        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> onNotFoundException(EntityNotFoundException e) {
        AppError error = new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VotingTimeIsUpException.class)
    ResponseEntity<?> onVoiceAlreadyExistException(VotingTimeIsUpException e) {
        AppError error = new AppError(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<?> onDataIntegrityViolationException(DataIntegrityViolationException e) {
        String errorMsg = Objects.requireNonNull(e.getRootCause()).getMessage().toLowerCase();
        AppError error = new AppError(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getRootCause().getLocalizedMessage());;
        for (Map.Entry<String,String> entry : CONSTRAINT_MAP.entrySet()) {
            if (errorMsg.contains(entry.getKey())) {
                error = new AppError(HttpStatus.UNPROCESSABLE_ENTITY.value(), entry.getValue());
            }
        }
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
