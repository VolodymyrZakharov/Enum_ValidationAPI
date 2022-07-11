package com.example.enum_validationapi.service;

import com.example.enum_validationapi.dto.ProjectRequestDTO;
import com.example.enum_validationapi.dto.ProjectResponseDTO;


public interface ProjectService {

    void createProject(ProjectRequestDTO request);

    ProjectResponseDTO findAll(Long Id, int pageSize, int pageNumber);




}
