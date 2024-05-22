package com.ak0411.filmfolio.domain.dtos;

public record UserTokenDto(
        String name,
        String username,
        String token
) {
}
