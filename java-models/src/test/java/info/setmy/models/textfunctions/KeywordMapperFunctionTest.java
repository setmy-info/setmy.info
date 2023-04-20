package info.setmy.models.textfunctions;

import info.setmy.models.textfunctions.register.KeywordFunctionMapper;
import info.setmy.models.textfunctions.register.KeywordFunctionMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordMapperFunctionTest {

    public static final String SOME_NON_EXISTING_KEYWORD = "some.non.existing.keyword";

    KeywordFunctionMapper keywordFunctionMapper;

    @BeforeEach
    public void before() {
        keywordFunctionMapper = KeywordFunctionMapperFactory.newInstance();
    }

    @Test
    public void nonExistingFunction() {
        assertThat(keywordFunctionMapper.get(SOME_NON_EXISTING_KEYWORD)).isNull();
        assertThat(keywordFunctionMapper.map(SOME_NON_EXISTING_KEYWORD, "something")).isEmpty();
    }

    @Test
    public void existingFunction() {
        assertThat(keywordFunctionMapper.get("int")).isNotNull();
        assertThat(keywordFunctionMapper.map("int", "123")).get().isEqualTo(123);
        assertThat(keywordFunctionMapper.map("Integer", "123")).get().isEqualTo(123);
    }
}
