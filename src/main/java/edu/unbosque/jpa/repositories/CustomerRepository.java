package edu.unbosque.jpa.repositories;
import edu.unbosque.jpa.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByFirst_name(String first_name);
    Optional<Customer> findByLast_name(String last_name);
    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();
    Optional<Customer> save(Customer costumer);
    void modify(String email,String firstName, String lastName, String gender, Integer age);

    void delete(String email);

}
