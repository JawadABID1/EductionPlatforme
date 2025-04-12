package ma.abid.eductionPlatform.services.courseFile.uploadStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UploadStrategyFactory {
    private final Map<String, UploadStrategy> strategies;

    public UploadStrategy getStrategy(String extension) {
        String capitalized = extension.substring(0, 1).toUpperCase() + extension.substring(1).toLowerCase();
        String key = "uploadStrategyImpl" + capitalized; // ex: strategyImplPdf, strategyImplMp4
        System.out.println("___________UploadStrategyName: " + key + "______________");
        UploadStrategy strategy = strategies.get(key);
        if (strategy == null) {
            throw new IllegalArgumentException("No uploadStrategy found for extension: " + extension);
        }
        return strategy;
    }

}
