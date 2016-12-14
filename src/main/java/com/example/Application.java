package com.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

    private static Logger logger = LogManager.getLogger("Application.class");

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, RoleRepository roleRepository) {
        return (args) -> {
            // save a couple of users
            Role adminRole = new Role("ADMIN");
            Role userRole = new Role("USER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            userRepository.save(new User("admin", "a", Arrays.asList(adminRole)));
            userRepository.save(new User("user1", "1", Arrays.asList(userRole)));
            userRepository.save(new User("user2", "2", Arrays.asList(userRole)));

        };
    }

}