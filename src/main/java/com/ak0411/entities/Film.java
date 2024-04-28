package com.ak0411.entities;

import com.ak0411.enums.Genre;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Table()
@Entity(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    private Set<Genre> genre;

    public Film() {
    }

    public Film(String title, Integer year, Set<Genre> genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film movie = (Film) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(year, movie.year) && genre == movie.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, genre);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                '}';
    }
}
