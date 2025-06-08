package com.calendarApp.calendarApp.controller;

import com.calendarApp.calendarApp.dto.TaskRequest;
import com.calendarApp.calendarApp.entity.Task;
import com.calendarApp.calendarApp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

//    @PostMapping
//    public ResponseEntity<Task> createTask(@ModelAttribute TaskRequest request, Principal principal) {
//        Task task = taskService.createTask(request, principal.getName());
//        return ResponseEntity.ok(task);
//    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
