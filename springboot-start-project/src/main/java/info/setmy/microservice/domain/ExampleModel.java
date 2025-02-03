package info.setmy.microservice.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.locationtech.jts.geom.Geometry;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@Accessors(chain = true)
@Table(name = "EXAMPLE")
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.NamedQuery(
    name = "findAll",
    query = "SELECT e FROM ExampleModel e"
)
public class ExampleModel implements Serializable {

    private static final String SEQUENCE_GENERATOR_NAME = "exampleSequenceGenerator";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, sequenceName = "EXAMPLE_ID_SEQ", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "TEXT", length = 512, nullable = false)
    //@jakarta.validation.constraints.Size(min = 1, max = 2)
    private String text;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transient
    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();

    @Column(name = "geom")
    private Geometry geom;

    @PostPersist
    public void postPersist() {
        System.out.println("Post persist");
    }

    @PostLoad
    public void postLoad() {
        System.out.println("Post load");
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("Post update");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("Post remove");
    }

    @PrePersist
    public void prePersist() {
        System.out.println("Pre persist");
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("Pre update");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("Pre remove");
    }

}
