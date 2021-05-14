package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.entities.Library;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EditionRepositoryImpl implements EditionRepository {

    private EntityManager entityManager;

    public EditionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Find an edition by id
     *
     * @param id the edition id
     * @return an optional of Edition
     */
    @Override
    public Optional<Edition> findById(Integer id) {
        Edition edition = this.entityManager.find(Edition.class, id);
        return edition != null ? Optional.of(edition) : Optional.empty();
    }

    /**
     * Finds all the editions of the DB
     *
     * @return a list of editions
     */
    @Override
    public List<Edition> findAll() {
        return entityManager.createQuery("from Edition").getResultList();
    }

    /**
     * Finds the editions related to a book
     *
     * @param id the id of the book
     * @return a lis of editions
     */
    @Override
    public List<Edition> findByBookId(Integer id) {
        return entityManager.createQuery("FROM Edition e WHERE e.id= :id").setParameter("id", id)
                .getResultList();
    }

    /**
     * Saves a new book to the DB
     *
     * @param edition the edition to be saved
     * @return an Optional of edition
     */
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

    /**
     * Modify the attributes of an specific edition
     *
     * @param editionId   the edition id to be modified
     * @param book        the new book
     * @param description the new description
     * @param releaseYear the new release year
     * @return a message of the result
     */
    @Override
    public String modify(Integer editionId, Book book, String description, Date releaseYear) {
        entityManager.getTransaction().begin();
        Optional<Edition> edition = this.findById(editionId);
        if (!edition.isPresent()) return "La edición del libro ingresada no existe!";

        edition.get().setBook(book);
        edition.get().setDescription(description);
        edition.get().setReleaseYear(releaseYear);
        entityManager.getTransaction().commit();

        return "Se ha modificado la edición exitosamente!";
    }

    /**
     * Delete an edition from the DB
     *
     * @param id the id of the edition to delete
     * @return a message of the result
     */
    @Override
    public String delete(Integer id) {
        entityManager.getTransaction().begin();

        Optional<Edition> edition = this.findById(id);
        // Verify if are any edition on the optional
        if (!edition.isPresent()) return "No se ha encontrado ninguna edición con ese id";

        // Remove the associations with the libraries
        for (Library library : edition.get().getLibraries())
            library.removeEdition(edition.get());

        entityManager.remove(edition.get());
        entityManager.getTransaction().commit();

        return "Se ha eliminado correctamente la edición";
    }
}
