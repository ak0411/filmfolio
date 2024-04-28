package com.ak0411;

import com.ak0411.services.DatabaseInitializationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        // Get the DatabaseInitializationService bean from the application context
        DatabaseInitializationService dbInitService = context.getBean(DatabaseInitializationService.class);

        // Call the methods to populate the tables
        dbInitService.populateFilmsTable();
        dbInitService.populateUsersTable();
    }
}
