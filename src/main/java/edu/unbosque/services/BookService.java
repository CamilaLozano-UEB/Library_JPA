package edu.unbosque.services;

import edu.unbosque.jpa.entities.Author;
import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.repositories.*;
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

    /**
     * Manages the list books method with an {@link EntityManager}
     *
     * @return a BookPOJO list with the information of the DB
     */
    public List<BookPOJO> listBooks() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        bookRepository = new BookRepositoryImpl(entityManager);
        // List of books (entity)
        List<Book> books = bookRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        // Convert the books list to an BookPOJO list
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

    /**
     * Manages the save method of the repository with an {@link EntityManager} and
     * add the book to an specific author
     *
     * @param title           the book title
     * @param isbn            the book ISBN
     * @param authorId        the author id
     * @param genre           the author genre
     * @param bookDescription the book description
     * @param releaseYear     the edition release year
     * @return a result message
     */
    public String saveBook(String title, String isbn, Integer authorId, String genre, String bookDescription, Date releaseYear) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);


        Optional<Author> author = authorRepository.findById(authorId);
        // Verify if are any author on the optional
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

    /**
     * Manages the modify method with an {@link EntityManager}, and removes previous author book and
     * assigns it to the new one
     *
     * @param bookId     the id of the book to modify
     * @param authorId   the new author id
     * @param title      the new title
     * @param isbnNumber the new ISBN number
     * @param genre      the new genre
     * @return a message of the result
     */
    public String modifyBook(Integer bookId, Integer authorId, String title, String isbnNumber, String genre) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);

        Optional<Author> author = authorRepository.findById(authorId);
        // Verify if are any author on the optional
        if (!author.isPresent()) return "El id del autor no existe!";

        Optional<Book> book = bookRepository.findById(bookId);
        // Verify if are any book on the optional
        if (!book.isPresent()) return "El id del libro no existe!";

        // Remove the book of the previous author
        authorRepository.findById(book.get().getAuthor().getAuthorId()).get().removeBook(book.get());

        // Modify and get the result message of the method modify
        String message = bookRepository.modify(bookId, authorId, title, isbnNumber, genre);

        // Assigns the book to the new author
        author.ifPresent(a -> {
            a.addBook(bookRepository.findById(bookId).get());
            authorRepository.save(a);
        });
        entityManager.close();
        entityManagerFactory.close();

        return message;
    }

    /**
     * Manages the delete method with an {@link EntityManager} using the bookRepository to delete the author book
     * and the editionRepository to delete all books editions
     *
     * @param bookId the id of the book to delete
     * @return a result message
     */
    public String deleteBook(Integer bookId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        authorRepository = new AuthorRepositoryImpl(entityManager);
        bookRepository = new BookRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);

        Optional<Book> book = bookRepository.findById(bookId);

        // Verify if are any book on the optional
        if (!book.isPresent()) return "No existe un libro con ese id";

        // Remove the book from the author book list
        authorRepository.findById(book.get().getAuthor().getAuthorId()).get().
                removeBook(bookRepository.findById(bookId).get());

        // find the editions of the book
        List<Edition> editions = editionRepository.findByBookId(bookId);

        // delete the editions of the book
        for (Edition edition : editions) editionRepository.delete(edition.getEditionId());

        // delete the book
        bookRepository.delete(bookId);
        entityManager.close();
        entityManagerFactory.close();

        return "Se ha eliminado el libro exitosamente!";
    }


}
