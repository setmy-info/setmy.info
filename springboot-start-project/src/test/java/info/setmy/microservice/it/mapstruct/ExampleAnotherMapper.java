package info.setmy.microservice.it.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING/*, uses = {ChangerComponent.class}*/)
public interface ExampleAnotherMapper {

    //@Mapping(target="changed", expression="java(changerComponent.change(source.getChanged()))")
    //@Mapping(source = "changed", target = "changed", qualifiedByName = "doChange")
    @Mapping(source = "anotherField", target = "differentField")
    DestinationExample toDestinationExample(SourceExample source);

    /*@AfterMapping
    default void doChange(SourceExample source, @MappingTarget DestinationExample destinationExample) {
        String newString = source.getChanged() + "!" + " ";
        String fullyChanged = destinationExample.getChanged() + newString;
        destinationExample.setChanged(fullyChanged);
    }*/
}
