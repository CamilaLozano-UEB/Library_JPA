package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Rent;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class RentRepositoryImpl implements RentRepository {

    private EntityManager entityManager;

    public RentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Rent> findByRent_id(Integer rentId) {
            Rent rent = entityManager.find(Rent.class, rentId);
            return rent != null ? Optional.of(rent) : Optional.empty();
    }

    @Override
    public Optional<Rent> findByRenting_date(String renting_date) {
        Rent rent = entityManager.createQuery("SELECT r FROM Rent r WHERE r.renting_date = :renting_date", Rent.class)
                .setParameter("renting_date", renting_date)
                .getSingleResult();
        return rent != null ? Optional.of(rent) : Optional.empty();
    }


    public List<Rent> findAll() {
        return entityManager.createQuery("from Rent").getResultList();
    }

    public Optional<Rent> save(Rent rent) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rent);
            entityManager.getTransaction().commit();
            return Optional.of(rent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
