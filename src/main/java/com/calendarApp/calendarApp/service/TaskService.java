package com.calendarApp.calendarApp.service;

import com.calendarApp.calendarApp.dto.TaskRequest;
import com.calendarApp.calendarApp.entity.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest taskRequest, String username);
    List<Task> getAllTasks();
}