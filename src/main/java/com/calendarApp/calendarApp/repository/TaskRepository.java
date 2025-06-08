package com.calendarApp.calendarApp.repository;

import com.calendarApp.calendarApp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByStatus(Task.Status status);

    @Query("SELECT COALESCE(MAX(t.id), 0) FROM Task t")
    int getMaxId();
}