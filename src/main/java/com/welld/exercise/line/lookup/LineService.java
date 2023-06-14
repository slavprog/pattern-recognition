package com.welld.exercise.line.lookup;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LineService {
    private final Set<Point> points = new HashSet<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public Set<Point> getAllPoints() {
        return points;
    }

    /**
     * Retrieves all line segments passing through at least N points.
     *
     * @param n the minimum number of points a line segment should pass through
     * @return a set of line segments passing through at least N points
     */
    public Set<LineSegment> getLinesWithNPoints(int n) {
        Map<SlopeIntercept, Set<Point>> slopeInterceptMap = new HashMap<>();

        // Calculate the slope and intercept for each pair of points
        for (Point p1 : points) {
            for (Point p2 : points) {
                if (p1 != p2) {
                    SlopeIntercept key = calculateSlopeIntercept(p1, p2);
                    Set<Point> linePoints = slopeInterceptMap.getOrDefault(key, new HashSet<>());

                    linePoints.add(p1);
                    linePoints.add(p2);

                    slopeInterceptMap.put(key, linePoints);
                }
            }
        }

        // Filter line segments with at least N points
        Set<LineSegment> lineSegments = new HashSet<>();
        for (Set<Point> linePoints : slopeInterceptMap.values()) {
            if (linePoints.size() >= n) {
                lineSegments.add(new LineSegment(linePoints));
            }
        }

        return lineSegments;
    }

    public void removeAllPoints() {
        points.clear();
    }

    /**
     * Calculates the slope and intercept of the line passing through two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the slope and intercept of the line as a {@link SlopeIntercept} object
     */
    private SlopeIntercept calculateSlopeIntercept(Point p1, Point p2) {
        double slope;
        double intercept;

        if (p1.getX().equals(p2.getX())) {
            // Handle vertical lines (infinite slope, no intercept)
            slope = Double.POSITIVE_INFINITY;
            intercept = p1.getX();
        } else {
            slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
            intercept = p1.getY() - slope * p1.getX();
        }

        return new SlopeIntercept(slope, intercept);
    }
}
