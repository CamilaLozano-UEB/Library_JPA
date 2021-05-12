package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Customer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class CustomerRepositoryImpl implements CustomerRepository {
    private EntityManager entityManager;

    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Customer> findByEmail(String email) {
        Customer customer = entityManager.find(Customer.class, email);
        return customer != null ? Optional.of(customer) : Optional.empty();
    }
    @Override
    public Optional<Customer> findByFirst_name(String first_name) {
        Customer customer = entityManager.createQuery("SELECT u FROM Customer u WHERE u.first_name = :first_name", Customer.class)
                .setParameter("first_name", first_name)
                .getSingleResult();
        return customer != null ? Optional.of(customer) : Optional.empty();
    }

    @Override
    public Optional<Customer> findByLast_name(String last_name) {
        Customer customer = entityManager.createQuery("SELECT u FROM Customer u WHERE u.last_name = :last_name", Customer.class)
                .setParameter("last_name", last_name)
                .getSingleResult();
        return customer != null ? Optional.of(customer) : Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("from Customer").getResultList();
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return Optional.of(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void modify(String email, String firstName, String lastName, String gender, Integer age) {
        entityManager.getTransaction().begin();
        Customer customer = this.findByEmail(email).get();
        if (customer == null) return;
        customer.setFirst_name(firstName);
        customer.setLast_name(lastName);
        customer.setGender(gender);
        customer.setAge(age.toString());
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String email) {
        entityManager.getTransaction().begin();
        Customer customer = this.findByEmail(email).get();
        if (customer == null) return;
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }
}
