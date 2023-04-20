package info.setmy.models.textfunctions.register;

import info.setmy.models.textfunctions.functions.Parameters;
import info.setmy.models.textfunctions.functions.Return;
import info.setmy.models.textfunctions.tokens.Template;
import info.setmy.models.textfunctions.tokens.Token;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static info.setmy.models.textfunctions.tokens.TokenType.PLACEHOLDER;
import static info.setmy.models.textfunctions.tokens.TokenType.USER_TEXT;

public class TemplateFunctionMapper {

    public static final String PLACEHOLDER_FINDING_REG_EXP = "(\\{[^}]+\\})|([^{}]+)";

    private final KeywordFunctionMapper keywordFunctionMapper;

    private final Map<Template, Function<Parameters, Return>> templateFunctions = new ConcurrentHashMap<>();

    public final Pattern placeholderPattern;

    public TemplateFunctionMapper(final KeywordFunctionMapper keywordFunctionMapper) {
        this.keywordFunctionMapper = keywordFunctionMapper;
        this.placeholderPattern = Pattern.compile(PLACEHOLDER_FINDING_REG_EXP);
    }

    public void put(final String templateString, final Function<Parameters, Return> function) {
        templateFunctions.put(toTemplate(templateString), function);
    }

    private Template toTemplate(final String templateString) {
        final Template template = new Template(templateString);
        final Matcher matcher = placeholderPattern.matcher(templateString);
        while (matcher.find()) {
            template.getTemplateList().add(toToken(matcher.group()));
        }
        return template;
    }

    private Token toToken(final String text) {
        if (isPlaceholderText(text)) {
            return new Token(PLACEHOLDER, text);
        } else {
            return new Token(USER_TEXT, text);
        }
    }

    private boolean isPlaceholderText(final String text) {
        if (text.length() >= 3) { // {a}
            final char[] chars = text.toCharArray();
            if (chars[0] == '{' && chars[chars.length - 1] == '}') {
                return true;
            }
        }
        return false;
    }

    public Optional<Template> getTemplate(final String templateString) {
        return templateFunctions.keySet().stream()
            .filter(template -> template.equals(templateString))
            .findFirst();
    }
}
