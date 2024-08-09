package info.setmy.templates.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
public abstract class BaseModel {

    private final TemplateConfig templateConfig;
    private final String templateName;
}
