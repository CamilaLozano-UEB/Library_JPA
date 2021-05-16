package edu.unbosque.services;

import edu.unbosque.jpa.entities.Customer;
import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.entities.Rent;
import edu.unbosque.jpa.repositories.*;
import edu.unbosque.servlets.pojos.RentPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Stateless
public class RentService {
    CustomerRepository costumerRepository;
    RentRepository rentRepository;
    EditionRepository editionRepository;

    /**
     *Manages the save method of the repository with an {@link EntityManager}
     *
     * @param email, the email of the costumer
     * @param edition_id,  the id of the edition
     * @param renting_date, the date of the rent
     * @return a result message
     */
    public String saveRent(String email, Integer edition_id, Date renting_date) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        costumerRepository = new CustomerRepositoryImpl(entityManager);
        rentRepository = new RentRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);

        Rent rent = new Rent(renting_date);
        Optional<Customer> customer = costumerRepository.findByEmail(email);
        //Verify that there is a customer searched for by that email
        if (!customer.isPresent()) return "El id del cliente ingresado no existe!";
        Optional<Edition> edition = editionRepository.findById(edition_id);
        //Verify that there is a edition searched for by the id
        if (!edition.isPresent())
            return "El id del edición ingresado no existe!";
        customer.ifPresent(a -> {
            a.addRent(rent);
            costumerRepository.save(a);
        });
        edition.ifPresent(e -> {
            e.addRent(rent);
            editionRepository.save(e);
        });
        entityManager.close();
        entityManagerFactory.close();
        return "Se ha guardado exitosamente";

    }

    /**
     * Manages the list rents between two specific dates and a customer method with an {@link EntityManager}
     *
     * @param renting_date1,renting_date2 the dates from search the rents
     * @param email, the email of the costumer
     * @return a RentPOJO list with the specific rents
     */
    public List<RentPOJO> listRents(Date renting_date1, Date renting_date2, String email) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        rentRepository = new RentRepositoryImpl(entityManager);
        List<Rent> rents;
        if (renting_date1.before(renting_date2)) {
            rents = rentRepository.findByRenting_date(renting_date1, renting_date2);
        } else {
            rents = rentRepository.findByRenting_date(renting_date2, renting_date1);
        }
        entityManager.close();
        entityManagerFactory.close();

        List<RentPOJO> rentPOJOList = new ArrayList<>();

        for (Rent rent : rents) {
            if (rent.getCustomer().getEmail().equals(email)) {
                rentPOJOList.add(new RentPOJO(
                        rent.getRentId(),
                        rent.getCustomer().getEmail(),
                        rent.getEdition().getEditionId(),
                        rent.getRenting_date()));
            }

        }
        return rentPOJOList;
    }
    /**
     * Manages the list of all rents with an {@link EntityManager}
     *
     *
     * @return a RentPOJO list with the rents
     */
    public List<RentPOJO> listAllRents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        rentRepository = new RentRepositoryImpl(entityManager);
        List<Rent> rents = rentRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<RentPOJO> rentPOJOList = new ArrayList<>();

        for (Rent rent : rents) {

            rentPOJOList.add(new RentPOJO(
                    rent.getRentId(),
                    rent.getCustomer().getEmail(),
                    rent.getEdition().getEditionId(),
                    rent.getRenting_date()));


        }
        return rentPOJOList;
    }

}
