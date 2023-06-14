package com.welld.exercise.line.lookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LineLookupResourceTest {

    @Test
    void testAddPoint() throws Exception {
        LineService lineService = mock(LineService.class);
        LineLookupResource lineController = new LineLookupResource(lineService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lineController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/point")
                        .content(asJsonString(new Point(1.0, 2.0)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testGetAllPoints() throws Exception {
        LineService lineService = mock(LineService.class);
        LineLookupResource lineController = new LineLookupResource(lineService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lineController).build();
        Set<Point> points = new HashSet<>();
        points.add(new Point(1.0, 2.0));
        points.add(new Point(3.0, 4.0));

        when(lineService.getAllPoints()).thenReturn(points);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/space"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetLinesWithNPoints() throws Exception {
        LineService lineService = mock(LineService.class);
        LineLookupResource lineController = new LineLookupResource(lineService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lineController).build();

        Set<Point> points = new HashSet<>();
        points.add(new Point(1.0, 1.0));
        points.add(new Point(2.0, 2.0));
        points.add(new Point(3.0, 3.0));

        LineSegment lineSegment = new LineSegment(points);
        Set<LineSegment> lineSegments = Collections.singleton(lineSegment);

        when(lineService.getLinesWithNPoints(3)).thenReturn(lineSegments);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/lines/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    void testRemoveAllPoints() throws Exception {
        LineService lineService = mock(LineService.class);
        LineLookupResource lineController = new LineLookupResource(lineService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lineController).build();

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/space"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

