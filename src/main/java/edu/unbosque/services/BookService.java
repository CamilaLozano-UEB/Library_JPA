package edu.unbosque.services;


import edu.unbosque.jpa.entities.Author;
import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.repositories.AuthorRepository;
import edu.unbosque.jpa.repositories.AuthorRepositoryImpl;
import edu.unbosque.jpa.repositories.BookRepository;
import edu.unbosque.jpa.repositories.BookRepositoryImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class BookService {

    AuthorRepository authorRepository;
    BookRepository bookRepository;

    public void saveBook(String title, String isbn, Integer authorId, String genre) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Optional<Author> author = authorRepository.findById(authorId);
        author.ifPresent(a -> {
            a.addBook(new Book(title, isbn, genre));
            authorRepository.save(a);
        });

        entityManager.close();
        entityManagerFactory.close();

    }

    public void modifyBook(Integer bookId, Integer authorId, String title, String isbnNumber, String genre) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);
        Optional<Author> author = authorRepository.findById(authorId);
        authorRepository.findById(bookRepository.findById(bookId).get().getAuthor().getAuthorId()).get().
                removeBook(bookRepository.findById(bookId).get());
        bookRepository.modify(bookId, authorId, title, isbnNumber, genre);
        System.out.println("dnjasnfllanflnlsf\nfnosdfocosbvocbs");
        author.ifPresent(a -> {
            a.addBook(new Book(title, isbnNumber, genre));
            authorRepository.save(a);
        });
        entityManager.close();
    }

    public void deleteBook(Integer bookId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        authorRepository.findById(bookRepository.findById(bookId).get().getAuthor().getAuthorId()).get().
                removeBook(bookRepository.findById(bookId).get());

        bookRepository.delete(bookId);

        entityManager.close();

    }


}
