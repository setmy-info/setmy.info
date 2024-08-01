package info.setmy.stealer.cli;

import info.setmy.stealer.exceptions.StealerException;
import info.setmy.stealer.models.StealerConfig;
import info.setmy.stealer.models.StepConfig;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static info.setmy.vcs.models.RepoType.valueOf;
import static java.lang.System.getProperty;
import static org.apache.commons.lang3.StringUtils.isBlank;

@NoArgsConstructor
public class StealerConfigService {

    private final String STEALER_DIR = ".stealer";
    private final String STEALER_CONFIG_FILE = "config.yaml";

    private final static StealerConfigService INSTANCE = new StealerConfigService();

    public static StealerConfigService getInstance() {
        return INSTANCE;
    }

    public StealerConfig getConfig() {
        final File currentDir = new File(getProperty("user.dir"));
        final File stealerDir = new File(currentDir, STEALER_DIR);
        final File stealerConfigFile = new File(stealerDir, STEALER_CONFIG_FILE);
        final StealerConfig result = getConfig(stealerConfigFile);
        return result;
    }

    private StealerConfig getConfig(final File stealerConfigFile) {
        final StealerConfig result = StealerConfig.builder()
            .workingDirectory(stealerConfigFile.getParentFile())
            .build();
        final List<StepConfig> stepConfigs = result.getStepConfigs();
        parseYamlToConfig(stealerConfigFile, stepConfigs);
        return result;
    }

    private void parseYamlToConfig(final File stealerConfigFile, final List<StepConfig> stepConfigs) {
        if (!stealerConfigFile.exists()) {
            throw new StealerException("Config file does not exist: " + stealerConfigFile.getAbsolutePath());
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(stealerConfigFile);
            final Yaml yaml = new Yaml();
            final Map<String, Object> obj = yaml.load(inputStream);
            final List steps = (List) obj.get("steps");
            confToStepConfigs(steps, stepConfigs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void confToStepConfigs(final List steps, final List<StepConfig> stepConfigs) {
        steps.stream()
            .map(step -> stepToStepConfig(step))
            .filter(mapped -> mapped != null)
            .forEach(stepx -> stepConfigs.add((StepConfig) stepx));
    }

    private StepConfig stepToStepConfig(final Object step) {
        if (step instanceof Map) {
            final Map<String, String> stepMap = (Map<String, String>) step;
            final StepConfig conf = StepConfig.builder()
                .url(toUrl(asNotNul(stepMap.get("url"))))
                .repoType(valueOf(asNotNul(stepMap.get("repoType")).trim().toUpperCase()))
                .directoryName(asNotNul(stepMap.get("directoryName")).trim())
                .branchName(asNotNul(stepMap.get("branchName")).trim())
                .build();
            // TODO
            return conf;
        }
        return null;
    }

    private String asNotNul(final String repoType) {
        if (isBlank(repoType)) {
            return "";
        }
        return repoType;
    }

    public static URL toUrl(final String urlString) {
        final URI uri = URI.create(urlString);
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
