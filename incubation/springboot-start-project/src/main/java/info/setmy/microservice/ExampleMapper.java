package info.setmy.microservice;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ExampleMapper {

    @Mapping(source = "text", target = "exampleString")
    ExampleDTO toDto(ExampleModel exampleModel);

    @Mapping(source = "exampleString", target = "text")
    ExampleModel toEntity(ExampleDTO exampleDTO);
}
