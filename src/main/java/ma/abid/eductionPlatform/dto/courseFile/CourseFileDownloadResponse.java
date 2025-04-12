package ma.abid.eductionPlatform.dto.courseFile;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class CourseFileDownloadResponse {
    private byte[] fileBytes;
    private String fileName;
    private String contentType;
}
