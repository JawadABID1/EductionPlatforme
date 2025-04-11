package ma.abid.eductionPlatforme.web;


import ma.abid.eductionPlatforme.dto.CourseFileDto;
import ma.abid.eductionPlatforme.dto.CourseFileUploadRequest;
import ma.abid.eductionPlatforme.services.courseFile.CourseFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course-files")
public class CourseFileController {
    private final CourseFileService service;

    public CourseFileController(CourseFileService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload/{courseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseFileDto> uploadFile(
            @PathVariable Long courseId,
            @ModelAttribute CourseFileUploadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.uploadFile(courseId, request.getFile()));
    }


    @GetMapping("/{fileId}")
    public ResponseEntity<CourseFileDto> getFile(@PathVariable Long fileId){
        return ResponseEntity.ok(service.getFile(fileId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseFileDto>> getFilesByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(service.getFilesByCourseId(courseId));
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId){
        return ResponseEntity.ok(service.downloadFile(fileId));
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId){
        service.deleteFile(fileId);
        return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully");
    }




}
