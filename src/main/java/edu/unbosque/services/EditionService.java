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

    public void saveEdition(Integer bookId, String description, Date releaseYear) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Book book = bookRepository.findById(bookId).get();
        if (book == null) return;

        Edition edition = new Edition(book, description, releaseYear);
        editionRepository.save(edition);

        entityManager.close();
        entityManagerFactory.close();
    }

    public void modifyEdition(Integer editionId, Integer bookId, String description, Date releaseYear) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Book book = bookRepository.findById(bookId).get();
        if (book == null) return;

        editionRepository.modify(editionId, book, description, releaseYear);

        entityManager.close();
        entityManagerFactory.close();
    }

    public void deleteEdition(Integer editionId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        editionRepository = new EditionRepositoryImpl(entityManager);
        editionRepository.delete(editionId);
        entityManager.close();
        entityManagerFactory.close();
    }
}
