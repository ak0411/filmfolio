package com.ak0411.filmfolio.dtos;

import com.ak0411.filmfolio.enums.Rating;

public record ReviewDto(String text, Rating rating) {
}
