package info.setmy.microservice.web.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.io.IOException;

// TODO : not finished : WKT - JSON serialization - deserialization
public class GeoJsonDeserializer extends JsonDeserializer<Geometry> {

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String wkt = jsonParser.getText();
        try {
            return new WKTReader().read(wkt);
        } catch (Exception e) {
            throw new IOException("Failed to parse WKT: " + wkt, e);
        }
    }
}
