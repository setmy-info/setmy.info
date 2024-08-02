package info.setmy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ${className} {
<!-- #assign attributeNames = classData.attributeNames -->

<!-- #list classData.attributeNames as attributeName -->
<#list attributeNames as attributeName>
    private String ${attributeName};
</#list>
}
