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
    public List<Customer> findAll() {
        return entityManager.createQuery("from Customer").getResultList();
    }

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

    @Override
    public String modify(String email, String firstName, String lastName, String gender, Integer age) {
        entityManager.getTransaction().begin();
        Optional<Customer> customer = this.findByEmail(email);
        if (!customer.isPresent()) return "No existe un cliente con el email ingresado!";
        customer.get().setFirst_name(firstName);
        customer.get().setLast_name(lastName);
        customer.get().setGender(gender);
        customer.get().setAge(age);
        entityManager.getTransaction().commit();
        return "Se ha modificado exitosamente!";
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
