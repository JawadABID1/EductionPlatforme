package ma.abid.eductionPlatform.web;


import ma.abid.eductionPlatform.dto.courseFile.CourseFileDownloadResponse;
import ma.abid.eductionPlatform.dto.courseFile.CourseFileDto;
import ma.abid.eductionPlatform.dto.courseFile.CourseFileUploadRequest;
import ma.abid.eductionPlatform.services.courseFile.CourseFileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course-files")
public class CourseFileController {
    private final CourseFileService service;

    public CourseFileController(CourseFileService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload/{courseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<CourseFileDto> uploadFile(
            @PathVariable Long courseId,
            @ModelAttribute CourseFileUploadRequest request) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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

    @GetMapping("/files/{fileId}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId){
        CourseFileDownloadResponse response = service.downloadFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(response.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getFileName() + "\"")
                .body(response.getFileBytes());
    }

    @DeleteMapping("/delete/{fileId}")
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId){
        service.deleteFile(fileId);
        return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully");
    }




}
