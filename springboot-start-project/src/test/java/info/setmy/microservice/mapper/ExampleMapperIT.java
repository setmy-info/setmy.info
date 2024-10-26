package info.setmy.microservice.mapper;

import info.setmy.microservice.domain.ExampleModel;
import info.setmy.microservice.it.SpringBase;
import info.setmy.microservice.web.dto.ExampleDTO;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
@Setter
public class ExampleMapperIT extends SpringBase {

    @Autowired
    private ExampleMapper exampleMapper;

    @Test
    public void toDto() throws Exception {
        ExampleModel exampleModel = new ExampleModel();
        exampleModel.setText("Hello World from DB");
        exampleModel.setGeom(new WKTReader().read("POINT (1 1)"));

        ExampleDTO exampleDTO = exampleMapper.toDto(exampleModel);

        assertThat(exampleDTO.getExampleString()).isEqualTo("Hello World from DB");
        assertThat(exampleDTO.getGeom()).isEqualTo("POINT (1 1)");
    }

    @Test
    public void toEntity() throws Exception {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setExampleString("Hello World from DB");
        exampleDTO.setGeom("POINT (1 1)");

        ExampleModel exampleModel = exampleMapper.toEntity(exampleDTO);

        assertThat(exampleModel.getText()).isEqualTo("Hello World from DB");
        assertThat(exampleModel.getGeom()).isEqualTo(new WKTReader().read("POINT (1 1)"));
    }

    @Test
    public void toDto_polygon() throws Exception {
        ExampleModel exampleModel = new ExampleModel();
        exampleModel.setText("Hello World from DB");
        exampleModel.setGeom(new WKTReader().read("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))"));

        ExampleDTO exampleDTO = exampleMapper.toDto(exampleModel);

        assertThat(exampleDTO.getExampleString()).isEqualTo("Hello World from DB");
        assertThat(exampleDTO.getGeom()).isEqualTo("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))");
    }

    @Test
    public void toEntity_polygon() throws Exception {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setExampleString("Hello World from DB");
        exampleDTO.setGeom("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))");

        ExampleModel exampleModel = exampleMapper.toEntity(exampleDTO);

        assertThat(exampleModel.getText()).isEqualTo("Hello World from DB");
        assertThat(exampleModel.getGeom()).isEqualTo(new WKTReader().read("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))"));
    }
}
