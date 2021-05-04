package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {
    private EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> findByEmail(String email) {
        User user = entityManager.find(User.class, email);
        return user != null ? Optional.of(user) : Optional.empty();
    }
    @Override
    public Optional<User> findByFirst_name(String first_name) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.first_name = :first_name", User.class)
                .setParameter("first_name", first_name)
                .getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public Optional<User> findByLast_name(String last_name) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.last_name = :last_name", User.class)
                .setParameter("last_name", last_name)
                .getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public Optional<User> save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
