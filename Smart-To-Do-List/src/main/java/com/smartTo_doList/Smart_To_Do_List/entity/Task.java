package com.smartTo_doList.Smart_To_Do_List.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 120)
    private String title;


    @Column(length = 1000)
    private String description;


    private LocalDate deadline; // ISO-8601 (yyyy-MM-dd)


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Priority priority = Priority.MEDIUM;


    @Column(nullable = false)
    private boolean completed = false;


    public enum Priority { LOW, MEDIUM, HIGH }


    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }


    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }


    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}