package ma.abid.eductionPlatform.services.courseFile.downloadStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DownloadStrategyFactory {
    private final Map<String, DownloadStrategy> strategies;

    public DownloadStrategy getStrategy(String extension){
        String capitalized = extension.substring(0, 1).toUpperCase() + extension.substring(1).toLowerCase();
        String key = "downloadStrategyImpl"+capitalized;
        System.out.println("______________________DownloadStrategyName: "+ key + "_______________________");
        DownloadStrategy downloadStrategy = strategies.get(key);

        if (downloadStrategy == null) throw new IllegalArgumentException("No uploadStrategy found for extension: " + extension);

        return downloadStrategy;
    }

}
