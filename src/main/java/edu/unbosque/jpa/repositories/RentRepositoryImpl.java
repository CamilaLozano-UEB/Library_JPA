package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Rent;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RentRepositoryImpl implements RentRepository {

    private EntityManager entityManager;

    /**
     * Constructor class with entity manager
     *
     * @param entityManager, entity manager of rent
     */
    public RentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Find the rents between two specific dates
     *
     * @param renting_date1,renting_date2 the dates from search the rents
     * @return
     */
    @Override
    public List<Rent> findByRenting_date(Date renting_date1, Date renting_date2 ) {
        Query rentQ = entityManager.createQuery("SELECT r FROM Rent r WHERE r.renting_date  BETWEEN : renting_date1   AND : renting_date2 ");
        rentQ.setParameter("renting_date1",renting_date1);
        rentQ.setParameter("renting_date2",renting_date2);
        List<Rent> rent =rentQ.getResultList();
        return rent ;
    }

    /**
     * Finds the rents from the email entered
     *
     * @param email the email from search the rents
     * @return
     */
    public List<Rent> findByEmail(String email) {
        Query rentQ = entityManager.createQuery("SELECT r FROM Rent r WHERE r.customer.id = :email");
        rentQ.setParameter("email",email);
        List<Rent> rent =rentQ.getResultList();
        return rent ;
    }

    /**
     * Finds the rents from the id of edition entered
     *
     * @param editionId the id of edition from search the rents
     * @return
     */
    public List<Rent> findByEdition(Integer editionId) {
        Query rentQ = entityManager.createQuery("SELECT r FROM Rent r WHERE r.edition.id= :editionId ");
        rentQ.setParameter("editionId",editionId);
        List<Rent> rent =rentQ.getResultList();
        return rent ;
    }

    /**
     * Finds all the rents of the DB
     *
     * @return a list of rents
     */
    public List<Rent> findAll() {
        return entityManager.createQuery("from Rent").getResultList();
    }

    /**
     *  Save a new rent to the DB
     *
     * @param rent the rent to be saved
     */
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
    /**
     * Delete a  rent from the DB
     *
     *  @param rent the rent to be deleted
     * */
    public void delete(Rent rent) {
        entityManager.getTransaction().begin();
        entityManager.remove(rent);
        entityManager.getTransaction().commit();
    }

}
