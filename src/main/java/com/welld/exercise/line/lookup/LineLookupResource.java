package com.welld.exercise.line.lookup;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Validated
@RequestMapping("/v1")
@Tag(name = "Line lookup API", description = "API endpoints for line lookup operations")
public class LineLookupResource {

    private final LineService lineService;

    public LineLookupResource(LineService lineService) {
        this.lineService = lineService;
    }

    @PostMapping("/point")
    @Operation(summary = "Add a point to the space")
    @ApiResponse(responseCode = "201", description = "Point added successfully")
    public ResponseEntity<Void> addPoint(@Valid @RequestBody Point point) {
        lineService.addPoint(point);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(value = "/space", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all points in the space")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved points")
    public ResponseEntity<Set<Point>> getAllPoints() {
        Set<Point> points = lineService.getAllPoints();
        return ResponseEntity.ok(points);
    }

    @GetMapping(value = "/lines/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all line segments passing through at least N points")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved line segments",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LineSegment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Line segments not found")
    })
    public ResponseEntity<Set<LineSegment>> getLinesWithNPoints(@PathVariable @Min(2) int n) {
        Set<LineSegment> lineSegments = lineService.getLinesWithNPoints(n);
        return ResponseEntity.ok(lineSegments);
    }

    @DeleteMapping("/space")
    @Operation(summary = "Remove all points from the space")
    @ApiResponse(responseCode = "204", description = "Points removed successfully")
    public ResponseEntity<Void> removeAllPoints() {
        lineService.removeAllPoints();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
