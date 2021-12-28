package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import helper.LocalDateDeserializer;
import helper.LocalDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Article")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {
    @Id
    @Column(name="articleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "creationDate", columnDefinition = "DATE")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creationDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "articleTypeId")
    private ArticleType type;

}
