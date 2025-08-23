package com.smartTo_doList.Smart_To_Do_List.dto;
import com.smartTo_doList.Smart_To_Do_List.entity.Task.Priority;
import jakarta.validation.constraints.*;


public class TaskRequest {
    @NotBlank
    @Size(max = 120)
    private String title;


    @Size(max = 1000)
    private String description;


    // yyyy-MM-dd; optional
    @Pattern(regexp = "^$|\\d{4}-\\d{2}-\\d{2}", message = "deadline must be yyyy-MM-dd")
    private String deadline;


    private Priority priority = Priority.MEDIUM;


    private Boolean completed = false;


    // getters & setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }


    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }


    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
}