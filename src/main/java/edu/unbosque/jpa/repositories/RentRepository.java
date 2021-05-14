package edu.unbosque.jpa.repositories;
import edu.unbosque.jpa.entities.Rent;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RentRepository {

    Optional<Rent> findByRent_id(Integer id);

    List<Rent> findByRenting_date(Date renting_date1, Date renting_date2 );

    List<Rent> findAll();

    Optional<Rent> save(Rent rent);

}
