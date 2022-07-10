package com.example.enum_validationapi.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskRequestDTO {

    @NotBlank
    @Length(min = 5, max = 30, message = "Task name should be not less than 5 and not more than 30 chars")
    private String taskName;

    @Max(value = 364, message = "Days to complete should be less than 365")
    @Min(value = 1, message = "Days to complete should be more than one")
    @NonNull
    private Integer daysToComplete;
}
