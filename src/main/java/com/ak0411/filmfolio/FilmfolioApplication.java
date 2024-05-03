package com.ak0411.filmfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class FilmfolioApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmfolioApplication.class, args);
    }
}
