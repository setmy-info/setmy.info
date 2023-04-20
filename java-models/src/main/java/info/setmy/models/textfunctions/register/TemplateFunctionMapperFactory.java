package info.setmy.models.textfunctions.register;

public class TemplateFunctionMapperFactory {

    public static TemplateFunctionMapper newInstance() {
        final TemplateFunctionMapper templateFunctionMapper = new TemplateFunctionMapper(
            KeywordFunctionMapperFactory.newInstance()
        );
        return templateFunctionMapper;
    }
}
