package com.ak0411.filmfolio.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String imdbId) {
        super("Could not find film with id: " + imdbId);
    }
}