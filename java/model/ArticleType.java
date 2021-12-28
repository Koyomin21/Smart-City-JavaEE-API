package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "ArticleType")
public class ArticleType {

    @Id
    @Column(name = "articleTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "articleType")
    private String type;

    @OneToMany(mappedBy = "type")
    List<Article> articles;
}
