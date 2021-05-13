package edu.unbosque.services;


import edu.unbosque.jpa.entities.Author;
import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.repositories.*;
import edu.unbosque.servlets.pojos.AuthorPOJO;
import edu.unbosque.servlets.pojos.BookPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Stateless
public class BookService {

    AuthorRepository authorRepository;
    BookRepository bookRepository;
    EditionRepository editionRepository;

    public List<BookPOJO> listBooks() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        bookRepository = new BookRepositoryImpl(entityManager);
        List<Book> books = bookRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<BookPOJO> bookPOJOList = new ArrayList<>();
        for (Book book : books) {
            bookPOJOList.add(new BookPOJO(
                    book.getBookId(),
                    book.getTitle(),
                    book.getIsbn(),
                    book.getAuthor().getAuthorId(),
                    book.getAuthor().getName(),
                    book.getGenre()
            ));
        }
        return bookPOJOList;
    }

    public String saveBook(String title, String isbn, Integer authorId, String genre, String bookDescription, Date releaseYear) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);


        Optional<Author> author = authorRepository.findById(authorId);
        if (!author.isPresent()) return "El id del autor ingresado no existe";
        author.ifPresent(a -> {
            Book book = new Book(title, isbn, genre);
            a.addBook(book);
            authorRepository.save(a);
            editionRepository.save(new Edition(book, bookDescription, releaseYear));
        });
        entityManager.close();
        entityManagerFactory.close();
        return "Se ha creado exitosamente el libro!";
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

        author.ifPresent(a -> {
            a.addBook(bookRepository.findById(bookId).get());
            authorRepository.save(a);
        });
        entityManager.close();
        entityManagerFactory.close();
    }

    public void deleteBook(Integer bookId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);

        authorRepository.findById(bookRepository.findById(bookId).get().getAuthor().getAuthorId()).get().
                removeBook(bookRepository.findById(bookId).get());

        List<Edition> editions = editionRepository.findAll();
        if (editions != null)
            for (int i = 0; i < editions.size(); i++)
                if (editions.get(i).getBook().getBookId() == bookId)
                    editionRepository.delete(editions.get(i).getEditionId());

        bookRepository.delete(bookId);
        entityManager.close();
        entityManagerFactory.close();
    }


}
