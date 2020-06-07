package info.setmy.microservice.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Entity
@Table(name = "EXAMPLE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleModel implements Serializable {

    private static final String SEQUENCE_GENERATOR_NAME = "exampleSequenceGenerator";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, sequenceName = "EXAMPLE_ID_SEQ", allocationSize = 1)
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "TEXT", length = 512, nullable = false)
    private String text;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transient
    private LocalDateTime dateTime = LocalDateTime.now();
}
