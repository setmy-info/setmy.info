package info.setmy.templates;

import info.setmy.templates.models.BaseModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
public class PoCAttribute extends BaseModel {

    private final String name;
}
