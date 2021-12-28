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
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name = "Users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "creationDate", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate creationDate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "UsersRoles",
            joinColumns = @JoinColumn(name="userId"),
            inverseJoinColumns = @JoinColumn(name = "roleid")
    )
    List<Role> userRoles;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Vacancy> vacancies;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<JobPost> jobPosts;

    @Override
    public String toString() {
        return "User: Username: " + this.getUsername();
    }
}
