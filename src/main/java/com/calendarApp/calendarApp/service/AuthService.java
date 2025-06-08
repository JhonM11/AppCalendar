package com.calendarApp.calendarApp.service;

import com.calendarApp.calendarApp.entity.User;

public interface AuthService {
    boolean login(String username, String password);
    User register(User user);
}
