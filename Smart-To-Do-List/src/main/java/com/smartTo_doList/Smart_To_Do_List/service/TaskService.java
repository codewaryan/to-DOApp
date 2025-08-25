// src/main/java/com/smartTo_doList/Smart_To_Do_List/service/TaskService.java
package com.smartTo_doList.Smart_To_Do_List.service;

import com.smartTo_doList.Smart_To_Do_List.dto.TaskRequest;
import com.smartTo_doList.Smart_To_Do_List.dto.TaskResponse;
import com.smartTo_doList.Smart_To_Do_List.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponse create(TaskRequest request);
    Page<TaskResponse> findAll(Boolean completed, Task.Priority priority, Pageable pageable);
    TaskResponse findById(Long id);
    TaskResponse update(Long id, TaskRequest request);
    boolean delete(Long id);
    Task updateCompletedStatus(Long id, Boolean completed);
}