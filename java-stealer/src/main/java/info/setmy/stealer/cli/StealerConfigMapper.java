package info.setmy.stealer.cli;

import info.setmy.stealer.models.Change;
import info.setmy.stealer.models.StepConfig;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static info.setmy.vcs.models.RepoType.valueOf;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.apache.commons.lang3.StringUtils.isBlank;

@NoArgsConstructor
public class StealerConfigMapper {

    private final static StealerConfigMapper INSTANCE = new StealerConfigMapper();

    public static StealerConfigMapper getInstance() {
        return INSTANCE;
    }

    public StepConfig stepToStepConfig(final Object step) {
        if (step instanceof Map) {
            final Map<String, Object> stepMap = (Map<String, Object>) step;
            final StepConfig conf = StepConfig.builder()
                .url(toUrl(asNotNul((String) stepMap.get("url"))))
                .repoType(valueOf(asNotNul((String) stepMap.get("repoType")).trim().toUpperCase()))
                .directoryName(asNotNul((String) stepMap.get("directoryName")).trim())
                .branchName(asNotNul((String) stepMap.get("branchName")).trim())
                .subDirectories(stepStringList((List<String>) stepMap.get("subDirectories")))
                .cleanup(stepStringList((List<String>) stepMap.get("cleanup")))
                .patches(stepStringList((List<String>) stepMap.get("patches")))
                .changes(toChangeList((List) stepMap.get("changes")))
                .build();
            return conf;
        }
        return null;
    }

    private List<String> stepStringList(final List<String> stringList) {
        if (stringList == null || stringList.isEmpty()) {
            return unmodifiableList(new ArrayList<>());
        }
        return stringList.stream().collect(toUnmodifiableList());
    }

    private List<Change> toChangeList(final List changes) {
        if (changes == null || changes.isEmpty()) {
            return unmodifiableList(new ArrayList<>());
        }
        final List<Change> result = new ArrayList<>();
        for (final Object c : changes) {
            final Change change = toChange(c);
            if (change != null) {
                result.add(change);
            }
        }
        return unmodifiableList(result);
    }

    private Change toChange(final Object changeObj) {
        if (changeObj instanceof Map) {
            final Map<String, Object> changeMap = (Map<String, Object>) changeObj;
            return Change.builder()
                .pattern(asNotNul((String) changeMap.get("pattern")))
                .replacement(asNotNul((String) changeMap.get("replacement")))
                .build();
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
