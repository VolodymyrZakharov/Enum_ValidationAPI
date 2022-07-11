package com.example.enum_validationapi.controller;

import com.example.enum_validationapi.dto.ProjectRequestDTO;
import com.example.enum_validationapi.dto.ProjectResponseDTO;
import com.example.enum_validationapi.dto.TaskResponseDTO;
import com.example.enum_validationapi.entity.types.TaskStatus;
import com.example.enum_validationapi.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public void createProject(@RequestBody @Valid ProjectRequestDTO request) {
        projectService.createProject(request);
    }

    @GetMapping("/project/{id}/task")
    public ProjectResponseDTO findAll(@PathVariable("id") Long id,
                                      @RequestParam int pageSize,
                                      @RequestParam int pageNumber) {
        return projectService.findAll(id, pageSize, pageNumber);
    }


}
