package com.ak0411.filmfolio.enums;

public enum Genre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    ADVENTURE("Adventure"),
    SCIENCE_FICTION("Science Fiction"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    ANIMATION("Animation"),
    MYSTERY("Mystery");

    private final String label;

    Genre(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.label;
    }
}
