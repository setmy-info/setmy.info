package info.setmy.microservice.mapper;

import info.setmy.microservice.domain.ExampleModel;
import info.setmy.microservice.web.dto.ExampleDTO;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ExampleMapper {

    @Mapping(source = "text", target = "exampleString")
    @Mapping(target = "geom", qualifiedByName = "convertGeometryToString")
    ExampleDTO toDto(ExampleModel exampleModel);

    @Mapping(source = "exampleString", target = "text")
    @Mapping(target = "geom", qualifiedByName = "convertStringToGeometry")
    ExampleModel toEntity(ExampleDTO exampleDTO);

    @Named("convertGeometryToString")
    default String convertGeometryToString(Geometry geometry) {
        return geometry != null ? new WKTWriter().write(geometry) : null;
    }

    @Named("convertStringToGeometry")
    default Geometry convertStringToGeometry(String geom) {
        try {
            return geom != null ? new WKTReader().read(geom) : null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert String to Geometry", e);
        }
    }
}
