package com.ak0411.filmfolio.entities;

import com.ak0411.filmfolio.enums.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    private Set<Genre> genre;

    public Film(String title, Integer year, Set<Genre> genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }
}
