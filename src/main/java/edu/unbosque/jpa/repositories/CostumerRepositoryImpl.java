package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Costumer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class CostumerRepositoryImpl implements CostumerRepository {
    private EntityManager entityManager;

    public CostumerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Costumer> findByEmail(String email) {
        Costumer costumer = entityManager.find(Costumer.class, email);
        return costumer != null ? Optional.of(costumer) : Optional.empty();
    }
    @Override
    public Optional<Costumer> findByFirst_name(String first_name) {
        Costumer costumer = entityManager.createQuery("SELECT u FROM Costumer u WHERE u.first_name = :first_name", Costumer.class)
                .setParameter("first_name", first_name)
                .getSingleResult();
        return costumer != null ? Optional.of(costumer) : Optional.empty();
    }

    @Override
    public Optional<Costumer> findByLast_name(String last_name) {
        Costumer costumer = entityManager.createQuery("SELECT u FROM Costumer u WHERE u.last_name = :last_name", Costumer.class)
                .setParameter("last_name", last_name)
                .getSingleResult();
        return costumer != null ? Optional.of(costumer) : Optional.empty();
    }

    @Override
    public List<Costumer> findAll() {
        return entityManager.createQuery("from Costumer").getResultList();
    }

    @Override
    public Optional<Costumer> save(Costumer costumer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(costumer);
            entityManager.getTransaction().commit();
            return Optional.of(costumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
