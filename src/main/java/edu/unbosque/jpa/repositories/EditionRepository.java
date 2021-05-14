package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EditionRepository {

    /**
     * Find an edition by id
     *
     * @param id the edition id
     * @return an optional of Edition
     */
    Optional<Edition> findById(Integer id);

    /**
     * Finds all the editions of the DB
     *
     * @return a list of editions
     */
    List<Edition> findAll();

    /**
     * Finds the editions related to a book
     *
     * @param id the id of the book
     * @return a lis of editions
     */
    List<Edition> findByBookId(Integer id);

    /**
     * Saves a new book to the DB
     *
     * @param edition the edition to be saved
     * @return an Optional of edition
     */
    Optional<Edition> save(Edition edition);

    /**
     * Modify the attributes of an specific edition
     *
     * @param editionId   the edition id to be modified
     * @param book        the new book
     * @param description the new description
     * @param releaseYear the new release year
     * @return a message of the result
     */
    String modify(Integer editionId, Book book, String description, Date releaseYear);

    /**
     * Delete an edition from the DB
     *
     * @param id the id of the edition to delete
     * @return a message of the result
     */
    String delete(Integer id);
}
