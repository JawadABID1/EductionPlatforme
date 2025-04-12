package ma.abid.eductionPlatform.services.courseFile.downloadStrategy;

import org.springframework.stereotype.Service;

@Service
public class DownloadStrategyImplPdf implements DownloadStrategy {
    @Override
    public String getContentType(String extension) {
        return "application/pdf";
    }
}
