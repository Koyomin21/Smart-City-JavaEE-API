package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int id;

    @Column(name="rolename")
    private String rolename;

    @ManyToMany(mappedBy = "userRoles")
    List<User> users;


    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role() {

    }

    @Override
    public String toString() {
        return "Role: " + this.rolename;
    }

}
