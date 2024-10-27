package info.setmy.microservice.web.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTWriter;

import java.io.IOException;

// TODO : not finished : WKT - JSON serialization - deserialization
public class GeoJsonSerializer extends JsonSerializer<Geometry> {

    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (geometry == null) {
            jsonGenerator.writeNull();
            return;
        }
        String wkt = new WKTWriter().write(geometry);
        jsonGenerator.writeString(wkt);
    }
}
