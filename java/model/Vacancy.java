package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Digits;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Vacancy")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vacancy {
    @Id
    @Column(name = "vacancyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vacancyId;

    @Column(name = "salary")
    @Digits(integer=7, fraction = 2)
    private BigDecimal salary;

    @Column(name = "description")
    private String description;

    @Column(name="isRelevant")
    private Boolean isRelevant;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
