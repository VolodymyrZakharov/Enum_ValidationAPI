package com.example.enum_validationapi.service;

import com.example.enum_validationapi.dto.TaskRequestDTO;
import com.example.enum_validationapi.dto.TaskResponseDTO;
import com.example.enum_validationapi.entity.types.TaskStatus;

import java.util.List;

public interface TaskService {

    void createTask(TaskRequestDTO request, Long projectId);

    void pushTaskForward(Long projectId, Long taskId);

    void pushTaskBackward(Long projectId, Long taskId);

    TaskResponseDTO findByName(Long projectId, String name);

    List<TaskResponseDTO> findByStatus(Long projectId, TaskStatus status);

    List<TaskResponseDTO> findAbandonedTasks(Long projectId);

}
