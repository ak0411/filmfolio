package com.ak0411.filmfolio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
class Error {
    private HttpStatus status;
    private String message;
}

@ControllerAdvice
class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({FilmNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Error handleNotFound(RuntimeException ex) {
        return new Error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({AlreadyReviewedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error handleAlreadyReviewed(RuntimeException ex) {
        return new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}

