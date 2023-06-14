package com.welld.exercise.line.lookup;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    @NotNull(message = "X coordinate is required")
    private Double x;
    @NotNull(message = "Y coordinate is required")
    private Double y;
}
