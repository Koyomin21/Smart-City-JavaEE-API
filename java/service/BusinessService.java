package service;


import model.Article;
import repository.BusinessRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BusinessService {

    @EJB
    private BusinessRepository repo;

    public List<Article> getBusinessArticles() {
        return repo.getBusinessArticles();
    }

    public Article getArticleById(int id) {
        return repo.getArticleById(id);
    }

    public Article getArticleBySubject(String subject) {
        return repo.getArticleBySubject(subject);
    }


}
