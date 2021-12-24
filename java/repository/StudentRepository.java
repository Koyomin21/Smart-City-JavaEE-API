package repository;


import aop.ExecutionTimeLogger;
import model.Facility;
import model.Location;
import model.User;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class StudentRepository extends Repository {
    public List<Facility> getAllUniversities() {
        List<Facility> universities = em.createQuery("SELECT f FROM Facility f")
                .getResultList();
        return universities.stream()
                .filter(u -> u.getType().getType().equals("University"))
                .collect(Collectors.toList());
    }

    public List<Facility> getAllLibraries() {
        List<Facility> universities = em.createQuery("SELECT f FROM Facility f")
                .getResultList();
        return universities.stream()
                .filter(u -> u.getType().getType().equals("Library"))
                .collect(Collectors.toList());
    }

    public List<User> getStudents() {
        try {
            List<User> tourists = em.createQuery("SELECT u FROM User u " +
                            "INNER JOIN u.userRoles ur " +
                            "WHERE ur.rolename = :role")
                    .setParameter("role", "STUDENT")
                    .getResultList();

            return tourists;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FAILED TO GET TOURISTS");
        }

        return null;
    }

    public Location getLocationOfUniversityById(int id) {
        try {
            Facility university = em.find(Facility.class, id);
            return university.getLocation();
        } catch (Exception e) {
            System.out.println("Failed to get location of uni");
            return null;
        }
    }



}
