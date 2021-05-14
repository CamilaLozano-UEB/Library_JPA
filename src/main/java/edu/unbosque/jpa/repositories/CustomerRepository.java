package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    /**
     * Optional type object that brings an object of type customer with a primary key of type email
     *
     * @param email, the primary key
     * @return return a customer email
     */
    Optional<Customer> findByEmail(String email);

    /**
     * List class method that finds all objects of a DB
     *
     * @return all the object of DB
     */
    List<Customer> findAll();

    /**
     * Method that saves a Customer type object
     *
     * @param costumer, customer type object
     * @return a saved customer object
     */
    String save(Customer costumer);

    /**
     * Modify the attributes of an specific customer
     *
     * @param email,      the id of the customer
     * @param first_name, the name of the customer
     * @param last_name,  the last name of the customer
     * @param gender,     the gender of the customer
     * @param age,        the age of the customer
     * @return a new modify saved object
     */
    String modify(String email, String first_name, String last_name, String gender, Integer age);

    /**
     * Method that removes a customer using an email type primary key
     *
     * @param email,the id of the customer
     */
    void delete(String email);

}
