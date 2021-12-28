package service;


import aop.ExecutionTimeLogger;
import model.Facility;
import model.Location;
import model.User;
import repository.StudentRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@ExecutionTimeLogger
public class StudentService {

    @EJB
    private StudentRepository repo;

    public List<Facility> getUniversities() {
        return repo.getAllUniversities();
    }
    public List<Facility> getLibraries() { return repo.getAllLibraries(); }
    public List<User> getStudents() { return repo.getStudents(); }
    public Location getLocationByUniversityId(int id) { return repo.getLocationOfUniversityById(id); }
    public List<Facility> getUniversitiesFilteredByName() {
        List<Facility> universities = repo.getAllUniversities();
        return universities.stream()
                .sorted(Comparator.comparing(Facility::getName))
                .collect(Collectors.toList());
    }
}
