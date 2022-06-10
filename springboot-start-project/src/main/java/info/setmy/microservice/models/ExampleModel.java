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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Entity
@Table(name = "EXAMPLE")
public class ExampleModel implements Serializable {

    private static final String SEQUENCE_GENERATOR_NAME = "exampleSequenceGenerator";

    public ExampleModel() {
    }

    public ExampleModel(final Long id, final String text, final LocalDateTime dateTime) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;
    }

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

    public Long getId() {
        return id;
    }

    public ExampleModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public ExampleModel setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ExampleModel setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
