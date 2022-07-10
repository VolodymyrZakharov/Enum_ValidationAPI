package com.example.enum_validationapi.repository;

import com.example.enum_validationapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
