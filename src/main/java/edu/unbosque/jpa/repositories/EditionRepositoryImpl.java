package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EditionRepositoryImpl implements EditionRepository {

    private EntityManager entityManager;

    public EditionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Edition> findById(Integer id) {
        Edition edition = this.entityManager.find(Edition.class, id);
        return edition != null ? Optional.of(edition) : Optional.empty();
    }

    @Override
    public List<Edition> findAll() {
        return entityManager.createQuery("from Author").getResultList();
    }

    @Override
    public Optional<Edition> save(Edition edition) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(edition);
            entityManager.getTransaction().commit();
            return Optional.of(edition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void modify(Integer editionId, Book book, String description, Date releaseYear) {
        entityManager.getTransaction().begin();
        Edition edition = this.findById(editionId).get();
        if (edition == null) return;
        edition.setBook(book);
        edition.setDescription(description);
        edition.setReleaseYear(releaseYear);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(this.findById(id));
        entityManager.getTransaction().commit();
    }
}
