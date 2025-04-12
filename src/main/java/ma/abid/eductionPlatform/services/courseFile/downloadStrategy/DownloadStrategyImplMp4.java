package ma.abid.eductionPlatform.services.courseFile.downloadStrategy;

import org.springframework.stereotype.Service;

@Service
public class DownloadStrategyImplMp4 implements DownloadStrategy {
    @Override
    public String getContentType(String extension) {
        return "video/mp4";
    }
}
