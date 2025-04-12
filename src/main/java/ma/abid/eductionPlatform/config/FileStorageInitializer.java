package ma.abid.eductionPlatform.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileStorageInitializer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        try {
            Path path = Paths.get(uploadDir);
            if (Files.notExists(path)) {
                Files.createDirectories(path);
                System.out.println("📁 Répertoire créé : " + path.toAbsolutePath());
            } else {
                System.out.println("📁 Répertoire déjà existant : " + path.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("❌ Impossible de créer le dossier de stockage de fichiers", e);
        }
    }
}

//
//Explication :
//@Configuration : indique que cette classe est une configuration Spring.
//
//@PostConstruct : exécute init() juste après le démarrage de l’application.
//
//@Value("${file.upload-dir}") : injecte la valeur définie dans application.properties.
