package com.example.enum_validationapi.service.impl;

import com.example.enum_validationapi.dto.TaskRequestDTO;
import com.example.enum_validationapi.dto.TaskResponseDTO;
import com.example.enum_validationapi.entity.Project;
import com.example.enum_validationapi.entity.Task;
import com.example.enum_validationapi.entity.types.TaskStatus;
import com.example.enum_validationapi.entity.types.TaskStatusConverter;
import com.example.enum_validationapi.repository.ProjectRepository;
import com.example.enum_validationapi.repository.TaskRepository;
import com.example.enum_validationapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void createTask(TaskRequestDTO request, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Task task = Task.builder()
                .taskName(request.getTaskName())
                .daysToComplete(request.getDaysToComplete())
                .taskStatus(TaskStatus.TODO)
                .project(project)
                .build();

        taskRepository.save(task);
    }

    @Override
    public void pushTaskForward(Long projectId, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (task.getTaskStatus().equals(TaskStatus.TODO)) {
            task.setTaskStatus(TaskStatus.IN_PROGRESS);
        } else if (task.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
            task.setTaskStatus(TaskStatus.DONE);
        }
        taskRepository.save(task);
    }

    @Override
    public void pushTaskBackward(Long projectId, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (task.getTaskStatus().equals(TaskStatus.DONE)) {
            task.setTaskStatus(TaskStatus.IN_PROGRESS);
        } else if (task.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
            task.setTaskStatus(TaskStatus.TODO);
        }
        taskRepository.save(task);
    }

    @Override
    public TaskResponseDTO findByName(Long projectId, String name) {
        List<Task> tasks = taskRepository.findAllByProjectId(projectId);

        Task task = tasks.stream().filter(x -> x.getTaskName().equals(name)).findFirst().get();
        return taskToDTO(task, projectId);
    }

    @Override
    public List<TaskResponseDTO> findByStatus(Long projectId, TaskStatus status) {
        List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        return tasks.stream().
                filter(x -> x.getTaskStatus().equals(status))
                .map(x -> taskToDTO(x, projectId))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDTO> findAbandonedTasks(Long projectId) {
        List<Task> tasks = taskRepository.findAllByProjectId(projectId);

        return tasks.stream().filter(x -> x.getTaskStatus().equals(TaskStatus.IN_PROGRESS))
                .filter(y -> y.getUpdatedOn().plus(7, ChronoUnit.DAYS).isBefore(LocalDateTime.now()))
                .map(z -> taskToDTO(z, projectId))
                .collect(Collectors.toList());

    }

    private TaskResponseDTO taskToDTO(Task task, Long projectId) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .daysToComplete(task.getDaysToComplete())
                .taskStatus(task.getTaskStatus())
                .createdOn(task.getCreatedOn())
                .updatedOn(task.getUpdatedOn())
                .projectId(projectId).build();
    }
}
