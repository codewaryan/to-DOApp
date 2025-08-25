package com.smartTo_doList.Smart_To_Do_List.service;


import com.smartTo_doList.Smart_To_Do_List.dto.TaskRequest;
import com.smartTo_doList.Smart_To_Do_List.dto.TaskResponse;
import com.smartTo_doList.Smart_To_Do_List.entity.Task;
import com.smartTo_doList.Smart_To_Do_List.exception.ResourceNotFoundException;
import com.smartTo_doList.Smart_To_Do_List.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repo;

    public TaskServiceImpl(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public TaskResponse create(TaskRequest request) {
        Task t = new Task();
        apply(t, request);
        return toDto(repo.save(t));
    }


    public Page<TaskResponse> findAll(Boolean completed, Task.Priority priority, Pageable pageable) {
        Page<Task> page = repo.findAll(pageable);
        List<TaskResponse> filtered = page.stream()
                .map(this::toDto)
                .filter(dto -> (completed == null || dto.isCompleted() == completed) &&
                        (priority == null || dto.getPriority().equals(priority.name())))
                .toList();
        return new org.springframework.data.domain.PageImpl<>(filtered, pageable, page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        Task t = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return toDto(t);
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        Task t = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        apply(t, request);
        return toDto(repo.save(t));
    }

    @Override
    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        repo.deleteById(id);
        return false;
    }

    @Override
    public Task updateCompletedStatus(Long id, Boolean completed) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        task.setCompleted(completed);
        return repo.save(task);
    }

    /** Apply incoming fields onto the entity (null-safe / partial updates). */
    private void apply(Task t, TaskRequest r) {
        if (r.getTitle() != null) t.setTitle(r.getTitle());
        if (r.getDescription() != null) t.setDescription(r.getDescription());
        if (r.getDeadline() != null && !r.getDeadline().isBlank()) {
            t.setDeadline(LocalDate.parse(r.getDeadline())); // expects yyyy-MM-dd
        }
        if (r.getPriority() != null) t.setPriority(r.getPriority());
        if (r.getCompleted() != null) t.setCompleted(r.getCompleted());
    }

    /** Map entity -> DTO returned to clients. */
    private TaskResponse toDto(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getDeadline() == null ? null : t.getDeadline().toString(),
                t.getPriority().name(),
                t.isCompleted()
        );
    }
}
