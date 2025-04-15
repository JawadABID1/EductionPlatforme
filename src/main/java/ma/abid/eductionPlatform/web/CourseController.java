package ma.abid.eductionPlatform.web;

import ma.abid.eductionPlatform.dto.course.CourseDto;
import ma.abid.eductionPlatform.services.course.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
   private final CourseService service;
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> getSearchedCourse(@RequestParam String key){
        return ResponseEntity.ok(service.getSearchedCourse(key));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(service.getCourseById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<CourseDto> createNewCourse(@RequestBody CourseDto courseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewCourse(courseDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto){
        return ResponseEntity.ok(service.updateCourse(id, courseDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PROF')")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}