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
        add(Film.builder().id("tt0440803").title("Shutter").year(2004).genre(Set.of(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)).build());
        add(Film.builder().id("tt0930083").title("Noroi").year(2005).genre(Set.of(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)).build());
        add(Film.builder().id("tt8119752").title("Gonjiam: Haunted Asylum").year(2018).genre(Set.of(Genre.HORROR)).build());
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
