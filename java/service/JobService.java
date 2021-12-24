package service;


import model.JobPost;
import model.Vacancy;
import repository.JobRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class JobService {

    @EJB
    private JobRepository repo;

    public List<Vacancy> getAllVacancies() {
        return repo.getVacancies();
    }

    public List<JobPost> getAllJobPosts() {
        return repo.getPosts();
    }

    public List<Vacancy> getVacanciesFilteredByRelevance() {
        List<Vacancy> vacancies = repo.getVacancies();
        return vacancies.stream()
                .filter(v -> v.getIsRelevant())
                .collect(Collectors.toList());
    }

    public int deleteJobPost(int id) {
        return repo.deleteJobPostById(id);
    }

    public int updateVacancyRelevance(int id, boolean relevance) {
        return repo.updateVacancyRelevanceById(id, relevance);
    }

    public int createJobPost(JobPost post) {
        return repo.createJobPost(post);
    }

    public boolean isVerified(JobPost post) {
        if(post.getSalary().subtract(new BigDecimal(1000000)).intValue() < 0) {
            return true;
        }

        return false;
    }

}
