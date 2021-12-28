package model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "FacilityType")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityType {
    @Id
    @Column(name = "facilityTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "facilityType")
    private String type;

    @OneToMany(mappedBy = "type")
    List<Facility> facilities;
}
