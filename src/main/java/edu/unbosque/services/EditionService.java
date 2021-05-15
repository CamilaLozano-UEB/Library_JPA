package edu.unbosque.services;

import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.repositories.BookRepository;
import edu.unbosque.jpa.repositories.BookRepositoryImpl;
import edu.unbosque.jpa.repositories.EditionRepository;
import edu.unbosque.jpa.repositories.EditionRepositoryImpl;
import edu.unbosque.servlets.pojos.EditionPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EditionService {
    EditionRepository editionRepository;
    BookRepository bookRepository;

    /**
     * Manages the list editions method with an {@link EntityManager}
     *
     * @return a list of EditionsPOJO on the DB
     */
    public List<EditionPOJO> listEditions() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);

        // List of editions (entity)
        List<Edition> editions = editionRepository.findAll();
        entityManager.close();
        entityManagerFactory.close();

        // Convert the editions list to an editionPOJO list
        List<EditionPOJO> editionPOJOList = new ArrayList<>();

        for (Edition edition : editions) {
            editionPOJOList.add(new EditionPOJO(
                    edition.getEditionId(),
                    edition.getDescription(),
                    edition.getReleaseYear(),
                    edition.getBook().getBookId(),
                    edition.getBook().getTitle()));
        }
        return editionPOJOList;
    }

    /**
     * Manages the save method of the repository with an {@link EntityManager} finding the book with the id
     *
     * @param bookId      the edition book id
     * @param description the edition description
     * @param releaseYear the edition release year
     * @return a result message
     */
    public String saveEdition(Integer bookId, String description, Date releaseYear) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Optional<Book> book = bookRepository.findById(bookId);
        // Verify if are any book on the optional
        if (!book.isPresent()) return "El id del libro ingresado no existe!";

        book.ifPresent(a -> {
            Edition edition = new Edition(book.get(), description, releaseYear);
            editionRepository.save(edition);
            bookRepository.save(a);
        });
        entityManager.close();
        entityManagerFactory.close();
        return "Se ha registrado correctamente la edición!";
    }

    /**
     * Manages the modify method of the repository with an {@link EntityManager}
     *
     * @param editionId   the id of the edition to be modified
     * @param bookId      the new book id
     * @param description the new edition description
     * @param releaseYear the new release year
     * @return a result message
     */
    public String modifyEdition(Integer editionId, Integer bookId, String description, Date releaseYear) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Optional<Book> book = bookRepository.findById(bookId);
        // Verify if are any book on the optional
        if (!book.isPresent()) return "El id del libro ingresado no existe";

        // Modify and get the result message of the method modify
        String message = editionRepository.modify(editionId, book.get(), description, releaseYear);
        entityManager.close();
        entityManagerFactory.close();

        return message;
    }

    /**
     * Manages the delete method with an {@link EntityManager}
     *
     * @param editionId the id of the edition to delete
     * @return a result message
     */
    public String deleteEdition(Integer editionId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        // delete and get the result message of the delete method
        String message = editionRepository.delete(editionId);
        entityManager.close();
        entityManagerFactory.close();

        return message;
    }
}
