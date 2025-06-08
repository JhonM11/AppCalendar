package com.calendarApp.calendarApp.service;

import com.calendarApp.calendarApp.entity.Task;
import com.calendarApp.calendarApp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskExecutorService {

    private final TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000) // cada minuto
    public void checkAndExecuteTasks() {
        List<Task> tasks = taskRepository.findAllByStatus(Task.Status.PENDIENTE);
        LocalDateTime now = LocalDateTime.now();

        tasks.stream()
                .filter(task -> !task.getExecutionTime().isAfter(now))
                .forEach(this::executeTask);
    }

    private void executeTask(Task task) {
        try {
            ProcessBuilder pb = new ProcessBuilder(task.getAbsolutePath());
            pb.start().waitFor();

            task.setStatus(Task.Status.EJECUTADA);
            taskRepository.save(task);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
