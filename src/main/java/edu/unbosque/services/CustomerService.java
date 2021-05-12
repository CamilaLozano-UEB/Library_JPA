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
                    Integer.parseInt(customer.getAge())
            ));
        }

        return customerPOJOS;
    }

    public Customer saveCustomer(String email,String firstName, String lastName, String gender, Integer age) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        customerRepository = new CustomerRepositoryImpl(entityManager);

        Customer customer = new Customer(email,firstName,lastName,  gender, age.toString());
        Customer persistedCustomer = customerRepository.save(customer).get();

        entityManager.close();

        return persistedCustomer;

    }
    public boolean findCustomer(String email) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        customerRepository = new CustomerRepositoryImpl(entityManager);

        if(customerRepository.findByEmail(email).equals(Optional.empty())){
            entityManager.close();
            return false;
        }else{
            return true;
        }

    }

    public void modifyCustomer(String email, String firstName, String lastName, String gender, Integer age) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        customerRepository.modify(email,firstName, lastName, gender, age);
        entityManager.close();
    }

    public void deleteCustomer(String email) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepositoryImpl(entityManager);
        customerRepository.delete(email);
        entityManager.close();
    }
}
