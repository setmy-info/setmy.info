package info.setmy.crawler.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Indexed //  Lucene
@Table(name = "record_entity")
@Builder(toBuilder = true)
public class RecordEntity {

    private static final String SEQUENCE_GENERATOR_NAME = "recordEntitySequenceGenerator";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, sequenceName = "RECORD_ENTITY_ID_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    //@FullTextField
    private String url;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content", columnDefinition = "JSON")
    //private String content;
    private Map<String, Object> content;
}
