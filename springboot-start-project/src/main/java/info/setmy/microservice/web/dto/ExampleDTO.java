package info.setmy.microservice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ExampleDTO {

    @NotBlank(message = "Example string is mandatory")
    @Size(min = 10, max = 200, message = "Example string must be between 10 and 200 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "Must contain only letters")
    private String exampleString;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"/*, timezone = "UTC"*/)
    private LocalDateTime dateTime;

    // TODO : WKT to JSON serializer and deserializer.
    /*
    @JsonSerialize(using = GeoJsonSerializer.class)
    private Geometry geom;
    */
    private String geom;
}
