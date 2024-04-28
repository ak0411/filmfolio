package com.ak0411.services;

import com.ak0411.dtos.SignUpDto;
import com.ak0411.entities.Film;
import com.ak0411.enums.Genre;
import com.ak0411.enums.UserRole;
import com.ak0411.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DatabaseInitializationService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private AuthService authService;

    private final List<Film> initialFilms = new ArrayList<Film>() {{
        add(new Film("Shutter", 2004, Set.of(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)));
        add(new Film("Noroi", 2005, Set.of(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)));
        add(new Film("Die Hard", 1988, Set.of(Genre.ACTION)));
        add(new Film("Home Alone", 1990, Set.of(Genre.COMEDY)));
        add(new Film("Forrest Gump", 1994, Set.of(Genre.DRAMA, Genre.ROMANCE)));
        add(new Film("The Lord of the Rings: The Fellowship of the Ring", 2001, Set.of(Genre.ADVENTURE, Genre.FANTASY)));
        add(new Film("The Matrix", 1999, Set.of(Genre.ACTION, Genre.SCIENCE_FICTION)));
        add(new Film("The Silence of the Lambs", 1991, Set.of(Genre.THRILLER)));
        add(new Film("Spirited Away", 2001, Set.of(Genre.ANIMATION)));
    }};

    private final List<SignUpDto> initialUsers = new ArrayList<SignUpDto>() {{
        add(new SignUpDto("admin", "password", UserRole.ADMIN));
        add(new SignUpDto("willy", "willywonka", UserRole.USER));
    }};

    public void populateFilmsTable() {
        filmRepository.saveAll(initialFilms);
    }

    public void populateUsersTable() {
        for (SignUpDto userData : initialUsers) {
            authService.signUp(userData);
        }
    }
}
