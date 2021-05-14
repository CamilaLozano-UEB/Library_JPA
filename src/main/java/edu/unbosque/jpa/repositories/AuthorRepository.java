package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    /**
     * Find an Author by id
     *
     * @param id the Author id to be searched
     * @return an optional object of author
     */
    Optional<Author> findById(Integer id);

    /**
     * Finds all the authors of the DB
     *
     * @return a list of Author
     */
    List<Author> findAll();

    /**
     * Saves a new Author to the DB
     *
     * @param author the author to be saved
     * @return a message of the result
     */
    String save(Author author);

    /**
     * Modify the attributes of an specific author
     *
     * @param id      the id of the author to be modified
     * @param name    the new name
     * @param country the new country
     * @return a message of the result
     */
    String modify(Integer id, String name, String country);

    /**
     * Delete an Author from th DB
     *
     * @param author the Author to delete
     */
    void delete(Author author);

}
