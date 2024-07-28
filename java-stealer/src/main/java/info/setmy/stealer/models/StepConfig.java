package info.setmy.stealer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class StepConfig extends HashMap<String, Object> {
}
