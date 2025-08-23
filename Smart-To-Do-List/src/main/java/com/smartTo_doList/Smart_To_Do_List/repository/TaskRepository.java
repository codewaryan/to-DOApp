package com.smartTo_doList.Smart_To_Do_List.repository;

import com.smartTo_doList.Smart_To_Do_List.entity.Task;
import com.smartTo_doList.Smart_To_Do_List.entity.Task.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
    List<Task> findByPriority(Priority priority);
}
