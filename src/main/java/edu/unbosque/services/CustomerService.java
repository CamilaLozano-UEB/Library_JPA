package edu.unbosque.services;


import edu.unbosque.jpa.entities.Customer;
import edu.unbosque.jpa.repositories.CustomerRepository;
import edu.unbosque.jpa.repositories.CustomerRepositoryImpl;
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

    public List<CustomerPOJO> listCustomer() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        customerRepository = new CustomerRepositoryImpl(entityManager);
        List<Customer> customers = customerRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

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

    public String modifyCustomer(String email, String first_name, String last_name, String gender, Integer age) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        String message = customerRepository.modify(email, first_name, last_name, gender, age);
        entityManager.close();
        entityManagerFactory.close();
        return message;
    }

    public String deleteCustomer(String email) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (!customer.isPresent()) return "No existe un cliente con el email ingresado!";
        customerRepository.delete(email);
        entityManager.close();
        entityManagerFactory.close();
        return "Se ha eliminado el cliente exitosamente!";

    }
}
