package ma.abid.eductionPlatforme.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class CourseFileUploadRequest {

    @Schema(type = "string", format = "binary")
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
