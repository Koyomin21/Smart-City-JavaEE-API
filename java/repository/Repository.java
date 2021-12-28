package repository;



import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateful
public class Repository {
    protected static String PERSISTENCE_UNIT_NAME = "default";
    protected static EntityManagerFactory factory = null;
    protected static EntityManager em = null;

    public Repository() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        System.out.println("Repo Started");
    }

}
