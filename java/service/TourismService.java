package service;

import model.Article;
import model.Facility;
import repository.TourismRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TourismService {

    @EJB
    private TourismRepository repo;

    public List<Facility> getTouristFacilities() {
        List<Facility> facilities = repo.getTheatres();
        facilities.addAll(repo.getATMs());
        facilities.addAll(repo.getHotels());
        return facilities;
    }

    public List<Facility> getATMs() {
        return repo.getATMs();
    }

    public List<Facility> getTheatres() {
        return repo.getTheatres();
    }

    public List<Facility> getHotels() {
        return repo.getHotels();
    }

    public List<Article> getTouristArticles() {
        return repo.getTouristArticles();
    }

 }
