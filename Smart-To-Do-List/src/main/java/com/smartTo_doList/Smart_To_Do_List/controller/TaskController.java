package com.smartTo_doList.Smart_To_Do_List.controller;

import com.smartTo_doList.Smart_To_Do_List.dto.ApiResponse;
import jakarta.validation.Valid;
import com.smartTo_doList.Smart_To_Do_List.dto.TaskRequest;
import com.smartTo_doList.Smart_To_Do_List.dto.TaskResponse;
import com.smartTo_doList.Smart_To_Do_List.entity.Task;
import com.smartTo_doList.Smart_To_Do_List.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

   /* @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody TaskRequest request) {
        return service.create(request);
    }
*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Task is added successfully!", service.create(request)));
    }

    @GetMapping
    public List<TaskResponse> findAll(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) Task.Priority priority
    ) {
        return service.findAll(completed, priority);
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        return service.update(id, request);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Task deleted successfully",
                    "id", id.toString()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", "error",
                            "message", "Task deleted successfully"
                    ));
        }
    }


    @PatchMapping("/{id}/completed")
    public ResponseEntity<ApiResponse<Task>> markAsCompleted(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request) {

        Boolean completed = request.get("completed");
        Task updatedTask = service.updateCompletedStatus(id, completed);

        return ResponseEntity.ok(new ApiResponse<>("Task status updated successfully!", updatedTask));
    }

}