package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.entities.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository {

    /**
     * list with the library objects in the DB
     *
     * @return library type objects in the DB
     */
    List<Library> findAll();

    /**
     * find an library type object  with the id
     *
     * @param libraryId, id library
     * @return an library object with the id selected
     */
    Optional<Library> findById(Integer libraryId);

    /**
     * Method that save a library
     *
     * @param library, library entity
     * @return library object
     */
    String save(Library library);

    /**
     * Method that modify a library
     *
     * @param libraryId, library id
     * @param name,      library name
     * @return modified library object
     */
    String modify(Integer libraryId, String name);

    /**
     * Method that remove a library
     *
     * @param libraryId, library id
     */
    void delete(Integer libraryId);

    /**
     * Method that associate a edition with a library
     *
     * @param edition, edition entity
     * @param library, library entity
     */
    void associateEdition(Edition edition, Library library);

    /**
     * Method that disassociate a edition with a library
     *
     * @param edition, edition entity
     * @param library, library entity
     */
    void disassociateEdition(Edition edition, Library library);

}
