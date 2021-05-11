package edu.unbosque.services;


import edu.unbosque.jpa.entities.Costumer;
import edu.unbosque.jpa.repositories.CostumerRepository;
import edu.unbosque.jpa.repositories.CostumerRepositoryImpl;
import edu.unbosque.servlets.pojos.CostumerPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CostumerService {

    CostumerRepository costumerRepository;

    public List<CostumerPOJO> listCostumer() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        costumerRepository = new CostumerRepositoryImpl(entityManager);
        List<Costumer> costumers = costumerRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<CostumerPOJO> costumerPOJOS = new ArrayList<>();
        for (Costumer costumer : costumers) {
            costumerPOJOS.add(new CostumerPOJO(
                    costumer.getEmail(),
                    costumer.getFirst_name(),
                    costumer.getLast_name(),
                    costumer.getGender(),
                    Integer.parseInt(costumer.getAge())
            ));
        }

        return costumerPOJOS;
    }

    public Costumer saveCostumer(String firstName, String lastName, String gender, Integer age) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        costumerRepository = new CostumerRepositoryImpl(entityManager);

        Costumer costumer = new Costumer(firstName,lastName,  gender, age.toString());
        Costumer persistedCostumer = costumerRepository.save(costumer).get();

        entityManager.close();

        return persistedCostumer;

    }

    public void modifyCostumer(String email,String firstName, String lastName, String gender, Integer age) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        costumerRepository = new CostumerRepositoryImpl(entityManager);
        costumerRepository.modify(email,firstName, lastName, gender, age);
        entityManager.close();
    }

    public void deleteAuthor(String email) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        costumerRepository = new CostumerRepositoryImpl(entityManager);
        costumerRepository.delete(email);
        entityManager.close();
    }
}
