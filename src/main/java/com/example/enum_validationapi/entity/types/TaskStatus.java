package com.example.enum_validationapi.entity.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    TODO(1, "todo"),
    IN_PROGRESS(2, "in_progress"),
    DONE(3, "done");

    private final Integer integerValue;
    private final String stringValue;

    public static TaskStatus findByIntegerValue(Integer statusId) {
        if (statusId == null) {
            return null;
        }

        return Arrays.stream(TaskStatus.values())
                .filter(x -> x.getIntegerValue().equals(statusId))
                .findFirst()
                .orElse(null);
    }

    @JsonCreator
    public static TaskStatus findByStringValue (String statusId) {
        if (statusId == null) {
            return null;
        }

        return Arrays.stream(TaskStatus.values())
                .filter(x -> x.getStringValue().equals(statusId))
                .findFirst()
                .orElse(null);
    }
}
