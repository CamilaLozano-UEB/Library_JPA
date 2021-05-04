package edu.unbosque.jpa.repositories;
import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository {

    Optional<Rent> findByRent_id(Integer id);

    Optional<Rent> findByRenting_date(String renting_date);

    List<Rent> findAll();

    Optional<Rent> save(Rent rent);

}
