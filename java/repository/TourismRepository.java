package repository;

import model.Article;
import model.Facility;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TourismRepository extends Repository{
    public List<Facility> getATMs() {
        List<Facility> atms = em.createQuery("SELECT f FROM Facility f")
                .getResultList();
        return atms.stream()
                .filter(u -> u.getType().getType().equals("ATM"))
                .collect(Collectors.toList());
    }

    public List<Facility> getTheatres() {
        List<Facility> theatres = em.createQuery("SELECT f FROM Facility f")
                .getResultList();
        return theatres.stream()
                .filter(u -> u.getType().getType().equals("Theatre"))
                .collect(Collectors.toList());
    }

    public List<Facility> getHotels() {
        List<Facility> hotels = em.createQuery("SELECT f FROM Facility f")
                .getResultList();
        return hotels.stream()
                .filter(u -> u.getType().getType().equals("Hotel"))
                .collect(Collectors.toList());
    }

    public List<Facility> getTouristFacilities() {
        List<Facility> facilities = em.createQuery("SELECT f FROM Facility f")
                .getResultList();
        return facilities.stream()
                .filter(u -> u.getType().getType().equals("Hotel") ||
                        u.getType().getType().equals("ATM") ||
                        u.getType().getType().equals("Theatre")
                )
                .collect(Collectors.toList());
    }

    public List<Article> getTouristArticles() {
        List<Article> articles = em.createQuery("SELECT a from Article a WHERE a.type.type=:type")
                .setParameter("type", "Tourism")
                .getResultList();
        return articles;
    }
}
