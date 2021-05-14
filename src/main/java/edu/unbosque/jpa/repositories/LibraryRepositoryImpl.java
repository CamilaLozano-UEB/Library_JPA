package edu.unbosque.jpa.repositories;


import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.entities.Library;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class LibraryRepositoryImpl implements LibraryRepository {

    private EntityManager entityManager;

    /**
     * Constructor class with entity manager
     *
     * @param entityManager, entity manager of library
     */
    public LibraryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Method that find and object with an id in the DB
     *
     * @return a query with the results
     */
    @Override
    public List<Library> findAll() {
        return entityManager.createQuery("from Library").getResultList();
    }

    /**
     * Method that find and object with an id in the DB
     *
     * @param libraryId, the primary key
     * @return the object with the primary key
     */
    @Override
    public Optional<Library> findById(Integer libraryId) {
        Library library = entityManager.find(Library.class, libraryId);
        return library != null ? Optional.of(library) : Optional.empty();
    }

    /**
     * Method that save a library object
     *
     * @param library, library entity
     * @return return a string message
     */
    @Override
    public String save(Library library) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(library);
            entityManager.getTransaction().commit();
            return "Se ha registrado exitosamente!";
        } catch (Exception e) {
            return "Ha ocurrido un error al registrar la biblioteca!";
        }
    }

    /**
     * Method that modify and save a saved library in the DB
     *
     * @param libraryId, library id
     * @param name,      library name
     * @return return a string message
     */
    @Override
    public String modify(Integer libraryId, String name) {
        entityManager.getTransaction().begin();
        //find a library optional object with the id
        Optional<Library> library = this.findById(libraryId);
        // Compare the founded id with the id entered, if exists, modify the library
        if (!library.isPresent()) return "No existe la libreria con el id ingresado!";
        library.get().setName(name);
        entityManager.getTransaction().commit();
        return "Se ha modificado exitosamente!";
    }

    /**
     * Method that remove and library in the DB
     *
     * @param libraryId, library id
     */
    @Override
    public void delete(Integer libraryId) {
        entityManager.getTransaction().begin();
        // Find the library with the PK
        Optional<Library> library = this.findById(libraryId);
        //If the searched object exist int the DB, search in the edition entity the editions of this library
        if (!library.isPresent()) return;
        Set<Edition> libraryEditions = library.get().getEditions();
        //the library edition is scrolled and all editions of that library are deleted
        for (Edition libraryEdition : libraryEditions)
            library.get().removeEdition(libraryEdition);
        //When all edits are deleted the library is deleted
        entityManager.remove(library.get());
        entityManager.getTransaction().commit();
    }

    /**
     * Associate a edition with a library
     *
     * @param edition, edition entity
     * @param library, library entity
     */
    @Override
    public void associateEdition(Edition edition, Library library) {
        entityManager.getTransaction().begin();
        library.addEdition(edition);
        entityManager.getTransaction().commit();
    }

    /**
     * Disassociate a edition with a library
     *
     * @param edition, edition entity
     * @param library, library entity
     */
    @Override
    public void disassociateEdition(Edition edition, Library library) {
        entityManager.getTransaction().begin();
        library.removeEdition(edition);
        entityManager.getTransaction().commit();
    }


}
