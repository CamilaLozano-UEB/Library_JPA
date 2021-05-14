package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Customer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class CustomerRepositoryImpl implements CustomerRepository {

    private EntityManager entityManager;

    /**
     * Constructor class with entity manager
     *
     * @param entityManager, entity manager of customer
     */
    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Method that find and object with an id in the DB
     *
     * @param email, the primary key
     * @return the object with the primary key
     */
    public Optional<Customer> findByEmail(String email) {
        Customer customer = entityManager.find(Customer.class, email);
        return customer != null ? Optional.of(customer) : Optional.empty();
    }

    /**
     * Method that return all of the customer objects in the DB
     *
     * @return a query with the results
     */
    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("from Customer").getResultList();
    }

    /**
     * Method that save a costumer object
     *
     * @param customer, customer type object
     * @return return a string message
     */
    @Override
    public String save(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return "Se ha registrado exitosamente!";
        } catch (Exception e) {
            return "Ha ocurrido un error al registrar al cliente!";
        }
    }

    /**
     * Method that modify and save a saved costumer in the DB
     *
     * @param email,      the id of the customer
     * @param first_name, the name of the customer
     * @param last_name,  the last name of the customer
     * @param gender,     the gender of the customer
     * @param age,        the age of the customer
     * @return a string message
     */
    @Override
    public String modify(String email, String first_name, String last_name, String gender, Integer age) {
        entityManager.getTransaction().begin();
        //find a customer optional object with the id
        Optional<Customer> customer = this.findByEmail(email);
        // Compare the founded id with the id entered, if exists, modify the customer
        if (!customer.isPresent()) return "No existe un cliente con el email ingresado!";
        customer.get().setFirst_name(first_name);
        customer.get().setLast_name(last_name);
        customer.get().setGender(gender);
        customer.get().setAge(age);
        entityManager.getTransaction().commit();
        return "Se ha modificado exitosamente!";


    }

    /**
     * Method that remove and customer in the DB
     *
     * @param email,the id of the customer
     */
    @Override
    public void delete(String email) {
        entityManager.getTransaction().begin();
        // Find the costumer with the PK
        Customer customer = this.findByEmail(email).get();
        //If the searched object exist int the DB, the customer is deleted
        if (customer == null) return;
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }
}
