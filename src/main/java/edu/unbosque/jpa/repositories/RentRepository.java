package edu.unbosque.jpa.repositories;
import edu.unbosque.jpa.entities.Rent;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RentRepository {
    /**
     * Finds the rents from the email entered
     *
     * @param email the email from search the rents
     * @return a list of rents
     * */
    List<Rent> findByEmail(String email);
    /**
     * Finds the rents from the id of edition entered
     *
     * @param editionId the id of edition from search the rents
     * @return a list of rents
     * */
    List<Rent> findByEdition(Integer editionId);
    /**
     * Find the rents between two specific dates
     *
     * @param renting_date1,renting_date2 the dates from search the rents
     * @return a list of rents
     * */
    List<Rent> findByRenting_date(Date renting_date1, Date renting_date2 );
    /**
     * Finds all the rents of the DB
     *
     * @return a list of rents
     * */
    List<Rent> findAll();
    /**
     * Save a new rent to the DB
     *
     *  @param rent the rent to be saved
     * */
    Optional<Rent> save(Rent rent);
    /**
     * Delete a  rent from the DB
     *
     *  @param rent the rent to be deleted
     * */
    void delete(Rent rent) ;
}
