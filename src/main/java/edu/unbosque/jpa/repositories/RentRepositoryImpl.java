package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Rent;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
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
    public List<Rent> findByRenting_date(Date renting_date1, Date renting_date2 ) {
        Query rentQ = entityManager.createQuery("SELECT r FROM Rent r WHERE r.renting_date  BETWEEN : renting_date1   AND : renting_date2 ");
        rentQ.setParameter("renting_date1",renting_date1);
        rentQ.setParameter("renting_date2",renting_date2);
        List<Rent> rent =rentQ.getResultList();
        return rent ;
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
