package com.ak0411.filmfolio;

//import com.ak0411.filmfolio.DatabaseInitializationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class FilmfolioApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FilmfolioApplication.class, args);

        // Get the DatabaseInitializationService bean from the application context
//        DatabaseInitializationService dbInitService = context.getBean(DatabaseInitializationService.class);
//
//        // Call the methods to populate the tables
//        dbInitService.populateFilmsTable();
//        dbInitService.populateUsersTable();
    }
}
