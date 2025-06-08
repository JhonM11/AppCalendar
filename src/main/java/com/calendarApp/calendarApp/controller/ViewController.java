package com.calendarApp.calendarApp.controller;

import com.calendarApp.calendarApp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final TaskService taskService;

    public ViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/tasks-view")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }
}
