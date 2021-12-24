package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Digits;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "JobPost")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobPost {
    @Id
    @Column(name = "postId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "salary")
    @Digits(integer=7, fraction = 2)
    private BigDecimal salary;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;


}
