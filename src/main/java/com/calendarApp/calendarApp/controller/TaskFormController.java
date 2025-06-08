package com.calendarApp.calendarApp.controller;

import com.calendarApp.calendarApp.dto.TaskRequest;
import com.calendarApp.calendarApp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TaskFormController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public String handleFormSubmission(@ModelAttribute TaskRequest request, Principal principal) {
        taskService.createTask(request, principal.getName());
        return "redirect:/tasks-view"; // Redirige de nuevo a la vista con la tabla actualizada
    }
}
