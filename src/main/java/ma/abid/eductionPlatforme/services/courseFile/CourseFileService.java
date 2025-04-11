package ma.abid.eductionPlatforme.services.courseFile;

import ma.abid.eductionPlatforme.dto.CourseFileDto;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseFileService {
    // Ajouter un fichier
    CourseFileDto uploadFile(Long courseId, MultipartFile file) throws ResourceNotFoundException;

    // Récupérer un fichier par ID
    CourseFileDto getFile(Long fileId) throws ResourceNotFoundException;

    // Récupérer une liste de fichiers pour un cours donné
    List<CourseFileDto> getFilesByCourseId(Long courseId) throws ResourceNotFoundException;

    // Télécharger un fichier par ID
    byte[] downloadFile(Long fileId) throws ResourceNotFoundException;

    // Supprimer un fichier
    void deleteFile(Long fileId) throws ResourceNotFoundException;
}
