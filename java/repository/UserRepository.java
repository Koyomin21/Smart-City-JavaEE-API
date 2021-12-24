package repository;

import model.Role;
import model.User;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class UserRepository extends Repository {
    public List<User> getAllUsers() {
        List<User> users = em.createQuery("SELECT u from User u")
                .getResultList();
        return users;
    }
    public List<User> getAllTourists() {

        try {
            List<User> tourists = em.createQuery("SELECT u FROM User u " +
                            "INNER JOIN u.userRoles ur " +
                            "WHERE ur.rolename = :role")
                    .setParameter("role", "TOURIST")
                    .getResultList();

            return tourists;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FAILED TO GET TOURISTS");
        }

        return null;
    }


    public User getUserById(int id) {
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.userId = :userId")
                .setParameter("userId", id).getSingleResult();
        return user;
    }

    public int login(String username, String password) {
        try {
            System.out.println(username + " : " + password);
            User user = (User) em.createQuery("SELECT u from User u WHERE u.username =:username AND u.password =:password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Transactional
    public int createUser(User user) {
        try {
            em.getTransaction().begin();
            Role defaultRole = (Role) em.createQuery("SELECT r FROM Role r WHERE r.rolename = :rolename")
                    .setParameter("rolename", "TOURIST")
                    .getSingleResult();
            user.setUserRoles(Arrays.asList(defaultRole));

            System.out.println(user.getUserRoles().size());
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }

        return 1;
    }


    public boolean isInRole(String username, String password, String role) {
        try {
            User user = (User) em.createQuery("SELECT u from User u WHERE u.username=:username AND u.password=:password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            boolean res = user.getUserRoles().stream()
                    .map(r -> r.getRolename().toLowerCase())
                    .filter(n -> n.equals(role.toLowerCase()))
                    .findFirst()
                    .isPresent();
            return res;

        } catch (Exception e) {
            System.out.println("Failed to check role");
            return false;
        }


    }

}
