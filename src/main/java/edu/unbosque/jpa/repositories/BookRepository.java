package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    /**
     * Find a Book by id
     *
     * @param id the book id to be searched book
     */
    Optional<Book> findById(Integer id);

    /**
     * Finds all the books of the DB
     *
     * @return a list of books
     */
    List<Book> findAll();

    /**
     * Saves a new book to the DB
     *
     * @param book the book to be saved
     * @return a message of the result
     */
    Optional<Book> save(Book book);

    /**
     * Modify the attributes of an specific book
     *
     * @param bookId     the id of the book to be modified
     * @param authorId   the new author id
     * @param title      the new title
     * @param isbnNumber the new ISBN number
     * @param genre      the new genre
     * @return a message of the result
     */
    String modify(Integer bookId, Integer authorId, String title, String isbnNumber, String genre);

    /**
     * Delete a Book from the DB
     *
     * @param bookId the id of the book to be deleted
     */
    void delete(Integer bookId);
}
