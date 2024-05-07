package com.ak0411.filmfolio.exceptions;

public class FilmAlreadyExistsException extends RuntimeException {
    public FilmAlreadyExistsException(String id) {
        super("Film with id " + id + " already exists");
    }
}
