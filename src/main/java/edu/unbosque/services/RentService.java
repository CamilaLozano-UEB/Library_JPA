package edu.unbosque.services;

import edu.unbosque.jpa.entities.Customer;
import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.entities.Rent;
import edu.unbosque.jpa.repositories.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class RentService {
    CustomerRepository costumerRepository;
    RentRepository rentRepository;
    EditionRepository editionRepository;

    public void saveRent(Integer rentId, String email, Integer edition_id, String renting_date) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        costumerRepository = new CustomerRepositoryImpl(entityManager);
        rentRepository = new RentRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);

        Rent rent = new Rent(rentId, renting_date);
        Optional<Customer> costumer = costumerRepository.findByEmail(email);
        costumer.ifPresent(a -> {
            a.addRent(rent);
            costumerRepository.save(a);
        });
        Optional<Edition> edition = editionRepository.findById(edition_id);
        edition.ifPresent(e -> {
            e.addRent(rent);
            editionRepository.save(e);
        });
        entityManager.close();
        entityManagerFactory.close();

    }
}
