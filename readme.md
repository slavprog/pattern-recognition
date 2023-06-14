### Given a set of N feature points in the plane, line lookup API provides possibility to determine every line that contains N or more of the points, and return all points involved. 
#### Available resources:
- Add a point to the space
> POST /point with body { "x": 1, "y": 2 }
- Get all points in the space
> GET /space
- Get all line segments passing through at least N points. Line segment is returned as a set of points.
> GET /lines/{n}
- Remove all points from the space
> DELETE /space

### Application can be started running command:
>./mvnw spring-boot:run

### For more details in regards to API, check http://localhost:8080/swagger-ui/index.html after application startup
