package com.ak0411.filmfolio.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(Long id) {
        super("Could not find film with id: " + id);
    }
}