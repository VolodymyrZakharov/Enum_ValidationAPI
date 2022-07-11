package com.example.enum_validationapi.service.impl;

import com.example.enum_validationapi.dto.ProjectRequestDTO;
import com.example.enum_validationapi.dto.ProjectResponseDTO;
import com.example.enum_validationapi.dto.TaskResponseDTO;
import com.example.enum_validationapi.entity.Project;
import com.example.enum_validationapi.entity.Task;
import com.example.enum_validationapi.repository.ProjectRepository;
import com.example.enum_validationapi.repository.TaskRepository;
import com.example.enum_validationapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void createProject(ProjectRequestDTO request) {
        Project project = Project.builder()
                .projectName(request.getProjectName())
                .build();
        projectRepository.save(project);
    }

    @Override
    public ProjectResponseDTO findAll(Long id, int pageSize, int pageNumber) {
        Project project = projectRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Pageable page = (Pageable) PageRequest.of(pageNumber, pageSize);
        List<Task> tasks = taskRepository.findAll((Sort) page);

        List<TaskResponseDTO> taskResponseDTOS = tasks.stream().map(x -> {
                    TaskResponseDTO taskResponseDTO = TaskResponseDTO.builder().
                            id(x.getId()).
                            taskName(x.getTaskName()).
                            daysToComplete(x.getDaysToComplete()).
                            taskStatus(x.getTaskStatus()).
                            createdOn(x.getCreatedOn()).
                            updatedOn(x.getUpdatedOn()).
                            projectId(project.getId()).
                            build();
                    return taskResponseDTO;
                }
        ).collect(Collectors.toList());

        return ProjectResponseDTO.builder().
                id(project.getId()).
                projectName(project.getProjectName()).
                createdOn(project.getCreatedOn()).
                updatedOn(project.getUpdatedOn()).
                tasks(taskResponseDTOS).
                build();
    }
}
