package edu.unbosque.services;

import edu.unbosque.jpa.entities.Customer;
import edu.unbosque.jpa.entities.Rent;
import edu.unbosque.jpa.repositories.*;
import edu.unbosque.servlets.pojos.CustomerPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class CustomerService {

    CustomerRepository customerRepository;

    /**
     * Manages the list customer method with an {@link EntityManager}
     *
     * @return the list of the customers on the DB
     */
    public List<CustomerPOJO> listCustomer() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        customerRepository = new CustomerRepositoryImpl(entityManager);
        // List of customer (entity)
        List<Customer> customers = customerRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        // Convert the customers list to an CustomerPOJO list
        List<CustomerPOJO> customerPOJOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerPOJOS.add(new CustomerPOJO(
                    customer.getEmail(),
                    customer.getFirst_name(),
                    customer.getLast_name(),
                    customer.getGender(),
                    customer.getAge()
            ));
        }

        return customerPOJOS;
    }

    /**
     * Manages the save method of the repository with an {@link EntityManager}
     *
     * @param email,      the email of the costumer
     * @param first_name, the name of the costumer
     * @param last_name,  the last name of the costumer
     * @param gender,     the gender of the costumer
     * @param age,        the age of the costumer
     * @return a result message
     */
    public String saveCustomer(String email, String first_name, String last_name, String gender, Integer age) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        Customer customer = new Customer(email, first_name, last_name, gender, age);
        String message = customerRepository.save(customer);
        entityManager.close();
        entityManagerFactory.close();

        return message;

    }

    /**
     * Find a customer with the email
     *
     * @param email, the email of costumer
     * @return a boolean
     */

    public boolean findCustomer(String email) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        if (customerRepository.findByEmail(email).equals(Optional.empty())) {
            entityManager.close();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Manages the modify method with an {@link EntityManager}
     *
     * @param email,      the email of the costumer
     * @param first_name, the name of the costumer
     * @param last_name,  the last name of the costumer
     * @param gender,     the gender of the costumer
     * @param age,        the age of the costumer
     * @return a result message
     */
    public String modifyCustomer(String email, String first_name, String last_name, String gender, Integer age) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        String message = customerRepository.modify(email, first_name, last_name, gender, age);
        entityManager.close();
        entityManagerFactory.close();
        return message;
    }

    /**
     * Manages the delete method with an {@link EntityManager}
     *
     * @param email, the id of the customer
     * @return a result message
     */
    public String deleteCustomer(String email) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        RentRepositoryImpl rentRepository = new RentRepositoryImpl(entityManager);
        EditionRepositoryImpl editionRepository = new EditionRepositoryImpl(entityManager);
        //find an optional object customer with the id email
        Optional<Customer> customer = customerRepository.findByEmail(email);
        //if the customer with the email exists, the customer will deleted
        if (!customer.isPresent()) return "No existe un cliente con el email ingresado!";

        List<Rent> rents= rentRepository.findByEmail(email);


        customer.get().getRents().clear();

        for (Rent rent : rents) {
            editionRepository.findById(rent.getEdition().getEditionId()).get().removeRents(rent);
            customer.get().removeRent(rent);
            rentRepository.delete(rent);

        }

        customerRepository.delete(email);
        entityManager.close();
        entityManagerFactory.close();
        return "Se ha eliminado el cliente exitosamente!";
    }
}
