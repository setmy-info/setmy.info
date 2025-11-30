package info.setmy.crawler.workflow;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class FLowContext {

    private final Map<String, Object> variables = new HashMap<>();
}
