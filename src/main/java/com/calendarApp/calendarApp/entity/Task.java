package com.calendarApp.calendarApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Task {
    @Id
    private Integer id;

    private LocalDateTime executionTime;
    private String absolutePath;
    private String createdBy;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDIENTE, FALLIDA, EJECUTADA
    }
}
