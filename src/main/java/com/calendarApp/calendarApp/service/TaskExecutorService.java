package com.calendarApp.calendarApp.service;

import com.calendarApp.calendarApp.entity.Task;
import com.calendarApp.calendarApp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskExecutorService {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecutorService.class);

    private final TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000)
    public void checkAndExecuteTasks() {
        logger.info("Verificando tareas pendientes para ejecutar...");

        List<Task> tasks = taskRepository.findAllByStatus(Task.Status.PENDIENTE);
        LocalDateTime now = LocalDateTime.now();

        tasks.stream()
                .filter(task -> !task.getExecutionTime().isAfter(now))
                .forEach(this::executeTask);

        logger.info("Verificación de tareas finalizada.");
    }

    private void executeTask(Task task) {
        String path = task.getAbsolutePath();
        File file = new File(path);

        if (!file.exists()) {
            logger.warn("❌ La ruta '{}' de la tarea ID {} no existe. Se omite la ejecución.", path, task.getId());
            return;
        }

        try {
            logger.info("▶️ Ejecutando tarea ID {} con ruta '{}'", task.getId(), path);

            ProcessBuilder pb = new ProcessBuilder(path);
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                logger.info("✅ Tarea ID {} ejecutada exitosamente.", task.getId());
                task.setStatus(Task.Status.EJECUTADA);
            } else {
                logger.warn("⚠️ Tarea ID {} terminó con código de salida: {}", task.getId(), exitCode);
                task.setStatus(Task.Status.FALLIDA);
            }

        } catch (IOException | InterruptedException e) {
            logger.error("🚨 Error ejecutando tarea ID {}: {}", task.getId(), e.getMessage());
            logger.debug("Stacktrace completo:", e);
            task.setStatus(Task.Status.FALLIDA);
        } finally {
            taskRepository.save(task);
        }
    }
}
