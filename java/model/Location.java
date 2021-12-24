package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="Location")
public class Location {
    @Id
    @Column(name = "locationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    List<Facility> facilities;
}
