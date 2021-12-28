package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Facility")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facilityId")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "facilityTypeId")
    private FacilityType type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "locationId")
    private Location location;

}
