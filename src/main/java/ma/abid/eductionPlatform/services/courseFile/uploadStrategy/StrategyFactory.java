package ma.abid.eductionPlatforme.services.courseFile.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StrategyFactory {
    private final Map<String, Strategy> strategies;

    public Strategy getStrategy(String extension) {
        String capitalized = extension.substring(0, 1).toUpperCase() + extension.substring(1).toLowerCase();
        String key = "strategyImpl" + capitalized; // ex: strategyImplPdf, strategyImplMp4

        Strategy strategy = strategies.get(key);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for extension: " + extension);
        }
        return strategy;
    }

}
