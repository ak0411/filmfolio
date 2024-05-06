package com.ak0411.filmfolio.config;

import com.ak0411.filmfolio.domain.dtos.SignUpDto;
import com.ak0411.filmfolio.domain.entities.Film;
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
        add(new Film("tt0440803", "Shutter", 2004, Set.of(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)));
        add(new Film("tt0930083", "Noroi", 2005, Set.of(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)));
        add(new Film("tt8119752", "Gonjiam: Haunted Asylum", 2018, Set.of(Genre.HORROR)));
    }};
    private final List<SignUpDto> initialUsers = new ArrayList<SignUpDto>() {{
        add(new SignUpDto(null, "admin", "password", UserRole.ADMIN));
        add(new SignUpDto("Will", "willy", "willywonka", UserRole.USER));
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
