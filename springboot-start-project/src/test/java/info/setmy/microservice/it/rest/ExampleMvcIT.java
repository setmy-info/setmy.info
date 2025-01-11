package info.setmy.microservice.it.rest;

import info.setmy.microservice.domain.ExampleModel;
import info.setmy.microservice.mapper.ExampleMapper;
import info.setmy.microservice.service.ExampleService;
import info.setmy.microservice.web.controller.ExampleController;
import info.setmy.microservice.web.dto.ExampleDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExampleController.class)
@ExtendWith(MockitoExtension.class)
public class ExampleMvcIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExampleService exampleService;

    @MockBean
    private ExampleMapper exampleMapper;

    @Test
    void exampleGet_shouldReturnExampleDto() throws Exception {
        ExampleModel model = new ExampleModel();
        ExampleDTO dto = new ExampleDTO().setExampleString("ExampleData").setDateTime(LocalDateTime.now());
        when(exampleService.getExampleModel()).thenReturn(model);
        when(exampleMapper.toDto(model)).thenReturn(dto);

        mockMvc.perform(
                get(ExampleController.EXAMPLE_REST_RESOURCE)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.exampleString").value("ExampleData"));

        verify(exampleService).getExampleModel();
        verify(exampleMapper).toDto(model);
    }
}
