package edu.unbosque.jpa.repositories;
import edu.unbosque.jpa.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();

    String save(Customer costumer);

    String modify(String email,String first_name, String last_name, String gender, Integer age);

    void delete(String email);

}
