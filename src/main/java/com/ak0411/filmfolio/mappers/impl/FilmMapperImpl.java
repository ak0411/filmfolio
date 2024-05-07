package com.ak0411.filmfolio.mappers.impl;

import com.ak0411.filmfolio.domain.dtos.FilmDto;
import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmMapperImpl implements Mapper<Film, FilmDto> {

    private final ModelMapper modelMapper;

    public FilmMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FilmDto mapTo(Film film) {
        return modelMapper.map(film, FilmDto.class);
    }

    @Override
    public Film mapFrom(FilmDto filmDto) {
        return modelMapper.map(filmDto, Film.class);
    }

    @Override
    public List<FilmDto> mapAll(Collection<Film> films) {
        return films.stream()
                .map(this::mapTo)
                .collect(Collectors.toList());
    }
}
