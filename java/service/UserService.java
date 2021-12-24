package service;

import aop.ExecutionTimeLogger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.User;
import repository.UserRepository;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Stateless
@ExecutionTimeLogger
public class UserService {
    @EJB
    private UserRepository repo;

    public List<User> getAllUsers() {
        return repo.getAllUsers();
    }

    public List<User> getAllTourists() {
        return repo.getAllTourists();
    }

    public User getUserById(int id) {
        return repo.getUserById(id);
    }

    public int createUser(User user) {
        if(user.getCreationDate() == null) user.setCreationDate(LocalDate.now());
        return repo.createUser(user);
    }

    public boolean isInRole(String username, String password, String rolename) {
        return repo.isInRole(username, password, rolename);
    }


    public int login(String username, String password) {
        return repo.login(username, password);
    }

    public String generateToken(String username, String password) {
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("username", username)
                .claim("password", password)
                .setSubject("user")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();
        return jwtToken;
    }

}
