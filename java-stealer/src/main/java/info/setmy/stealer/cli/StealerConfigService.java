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
import java.util.List;
import java.util.Map;

import static java.lang.System.getProperty;

@NoArgsConstructor
public class StealerConfigService {

    private final String STEALER_DIR = ".stealer";
    private final String STEALER_CONFIG_FILE = "config.yaml";

    private final static StealerConfigService INSTANCE = new StealerConfigService();

    private final StealerConfigMapper stealerConfigMapper = StealerConfigMapper.getInstance();

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
            .workingDirectory(stealerConfigFile.getParentFile().getParentFile())
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
            .map(step -> stealerConfigMapper.stepToStepConfig(step))
            .filter(step -> step != null)
            .forEach(step -> stepConfigs.add((StepConfig) step));
    }
}
