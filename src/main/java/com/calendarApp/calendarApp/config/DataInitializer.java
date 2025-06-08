package com.calendarApp.calendarApp.config;

import com.calendarApp.calendarApp.entity.User;
import com.calendarApp.calendarApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        final String defaultUsername = "admin";

        Optional<User> existingUser = userRepository.findByUsername(defaultUsername);
        if (existingUser.isEmpty()) {
            int nextId = userRepository.getMaxId() + 1;

            User user = new User();
            user.setId(nextId);
            user.setUsername(defaultUsername);
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setCreatedAt(LocalDateTime.now());

            userRepository.save(user);
            System.out.println("\u2705 Usuario admin creado correctamente con ID: " + nextId);
        } else {
            System.out.println("\u2139\uFE0F El usuario admin ya existe. No se creó nuevamente.");
        }
    }
}