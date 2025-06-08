package com.calendarApp.calendarApp.repository;

import com.calendarApp.calendarApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query("SELECT COALESCE(MAX(u.id), 0) FROM User u")
    int getMaxId();
}
