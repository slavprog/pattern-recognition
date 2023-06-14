package com.welld.exercise.line.lookup;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LineSegment {
    private Set<Point> points;
}
