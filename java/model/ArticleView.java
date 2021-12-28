package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import helper.LocalDateDeserializer;
import helper.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ArticleView {


    private String subject;

    private String description;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creationDate;

    private String articleType;


    public ArticleView() {}

    public ArticleView(Article article) {
        this.subject = article.getSubject();
        this.description = article.getDescription();
        this.articleType = article.getType().getType();
        this.creationDate = article.getCreationDate();
    }


}
