package com.smartTo_doList.Smart_To_Do_List.dto;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String deadline; // ISO-8601
    private String priority; // LOW/MEDIUM/HIGH
    private boolean completed;


    public TaskResponse(Long id, String title, String description, String deadline, String priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = completed;
    }


    // getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDeadline() { return deadline; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
}