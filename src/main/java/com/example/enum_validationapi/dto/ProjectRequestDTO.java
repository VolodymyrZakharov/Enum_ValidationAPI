package com.example.enum_validationapi.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectRequestDTO {

    @NotBlank(message = "Project name should not be blanked")
    private String projectName;
}
