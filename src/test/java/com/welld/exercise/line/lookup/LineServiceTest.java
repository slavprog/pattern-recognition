package com.welld.exercise.line.lookup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LineServiceTest {

    private LineService lineService;

    @BeforeEach
    void setUp() {
        lineService = new LineService();
    }

    @Test
    void testAddPoint() {
        Point point = new Point(1.0, 2.0);
        lineService.addPoint(point);

        Set<Point> points = lineService.getAllPoints();
        assertEquals(1.0, points.size());
        assertTrue(points.contains(point));
    }

    @Test
    void testGetAllPoints() {
        Point point1 = new Point(1.0, 2.0);
        Point point2 = new Point(3.0, 4.0);
        lineService.addPoint(point1);
        lineService.addPoint(point2);

        Set<Point> points = lineService.getAllPoints();
        assertEquals(2.0, points.size());
        assertTrue(points.contains(point1));
        assertTrue(points.contains(point2));
    }

    @Test
    void testGetLinesWithNPoints() {
        Point point1 = new Point(1.0, 1.0);
        Point point2 = new Point(2.0, 2.0);
        Point point3 = new Point(3.0, 3.0);
        Point point4 = new Point(4.0, 4.0);

        lineService.addPoint(point1);
        lineService.addPoint(point2);
        lineService.addPoint(point3);
        lineService.addPoint(point4);

        Set<LineSegment> lineSegments = lineService.getLinesWithNPoints(3);
        assertEquals(1.0, lineSegments.size());

        LineSegment lineSegment = lineSegments.iterator().next();
        assertTrue(lineSegment.getPoints().contains(point1));
        assertTrue(lineSegment.getPoints().contains(point2));
        assertTrue(lineSegment.getPoints().contains(point3));
    }

    @Test
    void testGetLinesWithNPointsManyPoints() {
        Point point1 = new Point(1.0, 1.0);
        Point point2 = new Point(2.0, 2.0);
        Point point3 = new Point(3.0, 3.0);
        Point point4 = new Point(4.0, 4.0);
        Point point5 = new Point(5.0, 5.0);
        Point point6 = new Point(3.0, 1.0);
        Point point7 = new Point(3.0, 2.0);
        Point point8 = new Point(3.0, 3.0);
        Point point9 = new Point(3.0, 4.0);
        Point point10 = new Point(3.0, 5.0);
        Point point11 = new Point(5.0, 1.0);
        Point point12 = new Point(4.0, 2.0);
        Point point13 = new Point(2.0, 4.0);
        Point point14 = new Point(1.0, 5.0);

        lineService.addPoint(point1);
        lineService.addPoint(point2);
        lineService.addPoint(point3);
        lineService.addPoint(point4);
        lineService.addPoint(point5);
        lineService.addPoint(point6);
        lineService.addPoint(point7);
        lineService.addPoint(point8);
        lineService.addPoint(point9);
        lineService.addPoint(point10);
        lineService.addPoint(point11);
        lineService.addPoint(point12);
        lineService.addPoint(point13);
        lineService.addPoint(point14);

        Set<LineSegment> lineSegments = lineService.getLinesWithNPoints(5);
        assertEquals(3.0, lineSegments.size());
    }

    @Test
    void testRemoveAllPoints() {
        Point point = new Point(1.0, 2.0);
        lineService.addPoint(point);
        lineService.removeAllPoints();

        Set<Point> points = lineService.getAllPoints();
        assertEquals(0, points.size());
    }
}

