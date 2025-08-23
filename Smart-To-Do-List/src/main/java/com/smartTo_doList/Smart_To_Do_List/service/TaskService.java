package com.smartTo_doList.Smart_To_Do_List.service;


import com.smartTo_doList.Smart_To_Do_List.dto.TaskRequest;
import com.smartTo_doList.Smart_To_Do_List.dto.TaskResponse;
import com.smartTo_doList.Smart_To_Do_List.entity.Task;

import java.util.List;

public interface TaskService {
    TaskResponse create(TaskRequest request);
    List<TaskResponse> findAll(Boolean completed, Task.Priority priority);
    TaskResponse findById(Long id);
    TaskResponse update(Long id, TaskRequest request);
    boolean delete(Long id);
    Task updateCompletedStatus(Long id, Boolean completed);
}
