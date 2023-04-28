package info.setmy.models.textfunctions;

import info.setmy.models.textfunctions.functions.Func;
import info.setmy.models.textfunctions.register.TemplateFunctionMapper;
import info.setmy.models.textfunctions.tokens.Template;
import info.setmy.models.textfunctions.tokens.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static info.setmy.models.textfunctions.functions.Return.newReturn;
import static info.setmy.models.textfunctions.register.TemplateFunctionMapperFactory.newInstance;
import static org.assertj.core.api.Assertions.assertThat;

class TemplateFunctionMapperTest {

    public static final String FANCY_PLACEHOLER_STRING = "This is actually {string} call with parameters {string} and {string}.";
    public static final String FANCY_ACTUAL_USR_STRING = "This is actually user textual function call with parameters abc, def, ghi and ijk, lmn.";

    public static final String FANCY_PLACEHOLER_STRING_1 = "abc{string}lmn";
    public static final String FANCY_ACTUAL_USR_STRING_2 = "abcdefghijklmn";

    TemplateFunctionMapper templateFunctionMapper;

    @BeforeEach
    public void before() {
        templateFunctionMapper = newInstance();
    }

    @Test
    public void placeholdersDetection2() {
        final Func fun = parameters -> newReturn(parameters.get(0));
        templateFunctionMapper.put(FANCY_PLACEHOLER_STRING_1, fun);
        final Template template = templateFunctionMapper.getTemplate(FANCY_PLACEHOLER_STRING_1).get();
        final List<Token> parsed = template.parse(FANCY_ACTUAL_USR_STRING_2);
        assertThat(parsed).isNotEmpty().hasSize(1);
        assertThat(parsed.get(0).getValue()).isEqualTo("defghijk");
    }

    @Test
    public void placeholdersDetection() {
        final Func fun = parameters -> newReturn(parameters.get(0) + "/" + parameters.get(1) + "/" + parameters.get(2));
        templateFunctionMapper.put(FANCY_PLACEHOLER_STRING, fun);
        final Optional<Template> optionalTemplate = templateFunctionMapper.getTemplate(FANCY_PLACEHOLER_STRING);
        assertThat(optionalTemplate).isPresent();
        final Template template = optionalTemplate.get();
        final List<Token> parsed = template.parse(FANCY_ACTUAL_USR_STRING);
        assertThat(parsed).isNotEmpty().hasSize(3);
        assertThat(parsed.get(0).getValue()).isEqualTo("user textual function");
        assertThat(parsed.get(1).getValue()).isEqualTo("abc, def, ghi");
        assertThat(parsed.get(2).getValue()).isEqualTo("ijk, lmn");
    }
}
