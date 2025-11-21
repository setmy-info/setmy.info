package info.setmy.crawler.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Indexed //  Lucene
public class Record {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //@FullTextField
    private String url;
    private String content;
}
