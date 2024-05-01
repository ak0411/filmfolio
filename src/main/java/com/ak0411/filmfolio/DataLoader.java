package com.ak0411.filmfolio;

import com.ak0411.filmfolio.dtos.SignUpDto;
import com.ak0411.filmfolio.entities.Film;
import com.ak0411.filmfolio.enums.Genre;
import com.ak0411.filmfolio.enums.UserRole;
import com.ak0411.filmfolio.repositories.FilmRepository;
import com.ak0411.filmfolio.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

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
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private AuthService authService;

    private void populateFilmsTable() {
        filmRepository.saveAll(initialFilms);
    }

    private void populateUsersTable() {
        for (SignUpDto userData : initialUsers) {
            authService.signUp(userData);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        populateFilmsTable();
        populateUsersTable();
    }
}
