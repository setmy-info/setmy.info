package info.setmy.models.textfunctions.register;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class KeywordFunctionMapper {

    private final Map<String, Function<String, Object>> keywordFunctions = new ConcurrentHashMap<>();

    private final Synonyms synonyms = new Synonyms();

    private final Set<String> allKeywords = new HashSet<>();

    public void put(final String key, final Function<String, Object> func) {
        keywordFunctions.put(key, func);
    }

    public Optional<Function<String, Object>> getFunction(final String key) {
        return ofNullable(get(key));
    }

    public Function<String, Object> get(final String key) {
        final Function<String, Object> result = keywordFunctions.get(synonyms.getKeywordBySynonym(key).orElse(key));
        return result;
    }

    public <T> Optional<T> map(final String keyword, final String value) {
        return getFunction(keyword)
            .map(stringObjectFunction -> (T) stringObjectFunction.apply(value));
    }

    public Synonyms getSynonyms() {
        return synonyms;
    }

    public Set<String> getAllKeywordPlaceholders() {
        return getAllKeywords().stream()
            .map(s -> "{" + s + "}")
            .collect(toUnmodifiableSet());
    }

    public Set<String> getAllKeywords() {
        if (!allKeywords.isEmpty()) {
            return allKeywords;
        }
        final Set<String> keywords = this.synonyms.getAllKeywords();
        final Set<String> moreKeywords = keywordFunctions.entrySet().stream()
            .map(stringFunctionEntry -> stringFunctionEntry.getKey())
            .collect(toUnmodifiableSet());
        allKeywords.addAll(keywords);
        allKeywords.addAll(moreKeywords);
        return allKeywords;
    }
}
