package com.example.enum_validationapi.dto;

import com.example.enum_validationapi.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectResponseDTO {

    private Long id;
    private String projectName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<TaskResponseDTO> tasks;
}
