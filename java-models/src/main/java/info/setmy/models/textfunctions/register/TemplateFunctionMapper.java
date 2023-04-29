package info.setmy.models.textfunctions.register;

import info.setmy.models.textfunctions.functions.Func;
import info.setmy.models.textfunctions.tokens.Template;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TemplateFunctionMapper {

    //private final KeywordFunctionMapper keywordFunctionMapper;

    private final Map<Template, Func> templateFunctions = new ConcurrentHashMap<>();

    /*
    public TemplateFunctionMapper(final KeywordFunctionMapper keywordFunctionMapper) {
        //this.keywordFunctionMapper = keywordFunctionMapper;
    }*/

    public void put(final String templateString, final Func function) {
        templateFunctions.put(toTemplate(templateString), function);
    }

    private Template toTemplate(final String templateString) {
        return new Template(templateString)
            .tokenize();
    }

    public Optional<Template> getTemplate(final String templateString) {
        return templateFunctions.keySet().stream()
            .filter(template -> template.equals(templateString))
            .findFirst();
    }
}
