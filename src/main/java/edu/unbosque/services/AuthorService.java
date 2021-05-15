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

    /**
     * Manages the list author method with an {@link EntityManager}
     *
     * @return the list of the authors on the DB
     */
    public List<AuthorPOJO> listAuthors() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        // List of author (entity)
        List<Author> authors = authorRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        // Convert the authors list to an AuthorPOJO list
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

    /**
     * Manages the save method of the repository with an {@link EntityManager}
     *
     * @param name    the name of the author
     * @param country the country of the author
     * @return a result message
     */
    public String saveAuthor(String name, String country) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);

        Author author = new Author(name, country);
        String message = authorRepository.save(author);

        entityManager.close();
        entityManagerFactory.close();

        return message;
    }

    /**
     * Manages the modify method with an {@link EntityManager}
     *
     * @param id      the author id to be modified
     * @param name    the new author name
     * @param country the new author country
     * @return a result message
     */
    public String modifyAuthor(Integer id, String name, String country) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        authorRepository = new AuthorRepositoryImpl(entityManager);
        String message = authorRepository.modify(id, name, country);
        entityManager.close();
        return message;
    }

    /**
     * Manages the delete method with an {@link EntityManager} using the bookRepository to delete the author books
     * and the editionRepository to delete all books editions
     *
     * @param id the author id to be deleted
     * @return a result message
     */
    public String deleteAuthor(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);

        Optional<Author> author = authorRepository.findById(id);

        // Verify if are any edition on the optional
        if (!author.isPresent()) return "No existe el autor con el id ingresado!";

        List<Book> books = bookRepository.findAuthorBooks(author.get());

        // Remove all the books on the author book list
        author.get().getBooks().clear();
        // Delete all books of the DB and the editions
        for (Book book : books) {
            for (Edition edition : editionRepository.findByBookId(book.getBookId()))
                // Delete all the editions off the book
                editionRepository.delete(edition.getEditionId());
            // Delete the book
            bookRepository.delete(book.getBookId());
        }

        authorRepository.delete(author.get());
        entityManager.close();
        return "Se ha eliminado el autor exitosamente!";
    }
}
