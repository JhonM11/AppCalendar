package com.calendarApp.calendarApp.service.impl;

import com.calendarApp.calendarApp.dto.TaskRequest;
import com.calendarApp.calendarApp.entity.Task;
import com.calendarApp.calendarApp.repository.TaskRepository;
import com.calendarApp.calendarApp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(TaskRequest taskRequest, String username) {
        int nextId = taskRepository.getMaxId() + 1;

        Task task = new Task();
        task.setId(nextId);
        task.setExecutionTime(taskRequest.getExecutionTime());
        task.setAbsolutePath(taskRequest.getAbsolutePath());
        task.setCreatedBy(username);
        task.setStatus(Task.Status.PENDIENTE);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
