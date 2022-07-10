package com.example.enum_validationapi.entity.types;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskStatus status) {
        return status == null ? null : status.getIntegerValue();
    }

    @Override
    public TaskStatus convertToEntityAttribute(Integer integer) {
        return integer == null ? null : TaskStatus.findByIntegerValue(integer);
    }
}
