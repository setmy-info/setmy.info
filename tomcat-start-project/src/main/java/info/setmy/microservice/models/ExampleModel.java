package info.setmy.microservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Entity
@Table(name = "EXAMPLE")
public class ExampleModel {

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

    public String getText() {
        return text;
    }

    public ExampleModel setText(String text) {
        this.text = text;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ExampleModel setId(Long id) {
        this.id = id;
        return this;
    }
}
