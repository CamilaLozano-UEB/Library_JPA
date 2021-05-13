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

    public List<EditionPOJO> listEditions() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);

        List<Edition> editions = editionRepository.findAll();
        entityManager.close();
        entityManagerFactory.close();

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

    public String saveEdition(Integer bookId, String description, Date releaseYear) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) return "El id del libro ingresado no existe!";

        Edition edition = new Edition(book.get(), description, releaseYear);
        editionRepository.save(edition);

        entityManager.close();
        entityManagerFactory.close();
        return "Se ha registrado correctamente la edición!";
    }

    public String modifyEdition(Integer editionId, Integer bookId, String description, Date releaseYear) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) return "El id del libro ingresado no existe";

        String message = editionRepository.modify(editionId, book.get(), description, releaseYear);

        entityManager.close();
        entityManagerFactory.close();

        return message;
    }

    public String deleteEdition(Integer editionId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        editionRepository = new EditionRepositoryImpl(entityManager);
        String message = editionRepository.delete(editionId);

        entityManager.close();
        entityManagerFactory.close();

        return message;
    }
}
