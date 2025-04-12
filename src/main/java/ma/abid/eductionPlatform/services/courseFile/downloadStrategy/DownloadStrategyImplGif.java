package ma.abid.eductionPlatform.services.courseFile.downloadStrategy;

import org.springframework.stereotype.Service;

@Service
public class DownloadStrategyImplGif implements DownloadStrategy {
    @Override
    public String getContentType(String extension) {
        return "image/gif";
    }
}
