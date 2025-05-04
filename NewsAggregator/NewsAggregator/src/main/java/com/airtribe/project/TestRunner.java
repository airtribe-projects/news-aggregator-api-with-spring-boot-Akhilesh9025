package com.airtribe.project;

import com.airtribe.project.Entity.User;
import com.airtribe.project.Repository.UserRepository;
import com.airtribe.project.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    public TestRunner(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Running Manual App Tests ===");

        try {
            // Simulate registration
            User newUser = new User();
            newUser.setUsername("driver_user");
            newUser.setPassword("password123");
            newUser.setRole("USER");

            User registered = userService.registerUser(newUser);
            System.out.println("Registered: " + registered.getUsername());

            userService.getByUsername("driver_user").ifPresentOrElse(
                    user -> System.out.println("User found: " + user.getUsername()),
                    () -> System.out.println("User not found.")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=== Manual Test Complete ===");
    }
}
