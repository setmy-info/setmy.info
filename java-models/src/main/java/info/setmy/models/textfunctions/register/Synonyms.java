package info.setmy.models.textfunctions.register;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

class Synonyms {

    private final List<Synonym> synonyms = new ArrayList<>();

    public boolean add(final String keyword, final String... synonyms) {
        return this.synonyms.add(new Synonym(asList(synonyms), keyword));
    }

    public Optional<String> getKeywordBySynonym(final String synonym) {
        return synonyms.stream()
            .filter(synonym1 -> synonym1.equals(synonym))
            .findFirst()
            .map(synonym1 -> synonym1.getSecond());
    }

    public Set<String> getAllKeywords() {
        final Set<String> keywords = synonyms.stream()
            .map(synonym -> synonym.getSecond())
            .collect(toSet());
        synonyms.forEach(synonym -> {
            keywords.addAll(synonym.getFirst());
        });
        return keywords;
    }
}
