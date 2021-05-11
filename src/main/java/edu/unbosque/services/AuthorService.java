package edu.unbosque.services;


import edu.unbosque.jpa.entities.Author;
import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.repositories.*;
import edu.unbosque.servlets.pojos.AuthorPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class AuthorService {

    AuthorRepository authorRepository;
    BookRepository bookRepository;
    EditionRepository editionRepository;

    public List<AuthorPOJO> listAuthors() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        List<Author> authors = authorRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<AuthorPOJO> authorsPOJO = new ArrayList<>();
        for (Author author : authors) {
            authorsPOJO.add(new AuthorPOJO(
                    author.getAuthorId(),
                    author.getName(),
                    author.getBooks().size(),
                    author.getCountry()
            ));
        }
        return authorsPOJO;
    }

    public Author saveAuthor(String name, String country) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);

        Author author = new Author(name, country);
        Author persistedAuthor = authorRepository.save(author).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedAuthor;

    }

    public void modifyAuthor(Integer id, String name, String country) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        authorRepository = new AuthorRepositoryImpl(entityManager);
        authorRepository.modify(id, name, country);
        entityManager.close();
    }

    public void deleteAuthor(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);

        Optional<Author> author = authorRepository.findById(id);

        if (!author.isPresent()) return;

        List<Book> books = bookRepository.findAll();
        List<Edition> editions = editionRepository.findAll();

        author.get().getBooks().clear();
        for (Book book : books) {
            if (book.getAuthor().getAuthorId().equals(author.get().getAuthorId())) {
                for (Edition edition : editions) {
                    if (edition.getBook().equals(book))
                        editionRepository.delete(edition.getEditionId());
                }
                bookRepository.delete(book.getBookId());
            }
        }

        authorRepository.delete(author.get());
        entityManager.close();
    }
}
