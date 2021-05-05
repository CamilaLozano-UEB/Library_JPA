package edu.unbosque.jpa.repositories;
import edu.unbosque.jpa.entities.Costumer;
import java.util.List;
import java.util.Optional;

public interface CostumerRepository {
    Optional<Costumer> findByFirst_name(String first_name);
    Optional<Costumer> findByLast_name(String last_name);
    Optional<Costumer> findByEmail(String email);

    List<Costumer> findAll();
    Optional<Costumer> save(Costumer costumer);


}
