package com.ak0411.filmfolio.exceptions;

public class AlreadyReviewedException extends RuntimeException {
    public AlreadyReviewedException() {
        super("You have already reviewed this film.");
    }
}
