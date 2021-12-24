package repository;

import model.JobPost;
import model.Vacancy;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class JobRepository extends Repository{
    public List<Vacancy> getVacancies() {
        return em.createQuery("SELECT v from Vacancy v").getResultList();
    }

    public List<JobPost> getPosts() {
        return em.createQuery("SELECT p from JobPost p").getResultList();
    }

    public int deleteJobPostById(int id) {
        try {
            em.getTransaction().begin();
            int res = em.createQuery("DELETE FROM JobPost p WHERE p.postId =:id")
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to delete Job Post");
            return 0;
        }
    }


    public int updateVacancyRelevanceById(int id, boolean relevance) {
        try {
            em.getTransaction().begin();

            Vacancy vacancy = em.find(Vacancy.class, id);
            vacancy.setIsRelevant(relevance);
            em.persist(vacancy);

            em.getTransaction().commit();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to update Vacancy");
            return 0;
        }
    }

    public int createJobPost(JobPost post) {
        try {
            em.getTransaction().begin();
            em.persist(post);
            em.getTransaction().commit();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create Job Post");
            return 0;
        }
    }
}
