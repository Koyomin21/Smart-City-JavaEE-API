package repository;

import model.Article;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BusinessRepository extends Repository {
    public List<Article> getBusinessArticles() {
        return em.createQuery("SELECT a from Article a WHERE a.type.type =:type")
                .setParameter("type", "Business")
                .getResultList();
    }

    public Article getArticleBySubject(String subject) {
        try {

            return (Article) em.createQuery("SELECT a from Article a WHERE a.subject =:subject")
                    .setParameter("subject", subject)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Failed to get Article by subject");
            return null;
        }
    }

    public Article getArticleById(int id) {
        return em.find(Article.class, id);
    }


}
