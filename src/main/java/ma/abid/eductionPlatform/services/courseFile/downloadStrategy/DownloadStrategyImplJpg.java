package ma.abid.eductionPlatform.services.courseFile.downloadStrategy;

import org.springframework.stereotype.Service;

@Service
public class DownloadStrategyImplJpg implements DownloadStrategy {
    @Override
    public String getContentType(String extension) {
        return "image/jpeg";
    }
}
