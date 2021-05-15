package edu.unbosque.services;


import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.entities.Library;
import edu.unbosque.jpa.repositories.EditionRepository;
import edu.unbosque.jpa.repositories.EditionRepositoryImpl;
import edu.unbosque.jpa.repositories.LibraryRepository;
import edu.unbosque.jpa.repositories.LibraryRepositoryImpl;
import edu.unbosque.servlets.pojos.AssociationPOJO;
import edu.unbosque.servlets.pojos.LibraryPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class LibraryService {

    LibraryRepository libraryRepository;
    EditionRepository editionRepository;

    /**
     * Manages the list library method with an {@link EntityManager}
     *
     * @return the list of the customers on the DB
     */

    public List<LibraryPOJO> listLibraries() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        libraryRepository = new LibraryRepositoryImpl(entityManager);
        // List of library (entity)
        List<Library> libraries = libraryRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();
        // Convert the libraries list to an LibraryPOJO list
        List<LibraryPOJO> librariesPOJO = new ArrayList<>();
        for (Library library : libraries) {
            librariesPOJO.add(new LibraryPOJO(
                    library.getLibraryId(),
                    library.getName()
            ));
        }

        return librariesPOJO;

    }

    /**
     * Manages the save method of the repository with an {@link EntityManager}
     *
     * @param name, the name of the library
     * @return a result message
     */
    public String saveLibrary(String name) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);
        Library library = new Library(name);
        String message = libraryRepository.save(library);
        entityManager.close();
        entityManagerFactory.close();

        return message;

    }

    /**
     * Manages the modify method with an {@link EntityManager}
     *
     * @param libraryId, id of the library
     * @param name,      name of the library
     * @return a result message
     */
    public String modifyLibrary(Integer libraryId, String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);
        String message = libraryRepository.modify(libraryId, name);
        entityManager.close();
        entityManagerFactory.close();
        return message;
    }

    /**
     * Manages the delete method with an {@link EntityManager}
     *
     * @param libraryId, id of the library
     * @return a message result
     */
    public String deleteLibrary(Integer libraryId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);
        // find a library optional with the id
        Optional<Library> library = libraryRepository.findById(libraryId);
        // Verify if are any library on the optional, if exists, the library will deleted
        if (!library.isPresent()) return "No existe la biblioteca con el id ingresado!";
        libraryRepository.delete(libraryId);
        entityManager.close();
        entityManagerFactory.close();
        return "Se ha eliminado la biblioteca exitosamente!";

    }

    /**
     * Manages the associate method with an {@link EntityManager}
     *
     * @param libraryId, the library id
     * @param editionId, the edition id
     * @return a result message
     */
    public String associateEdition(Integer libraryId, Integer editionId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);
        Optional<Library> library = libraryRepository.findById(libraryId);
        //Verify the library is exists
        if (!library.isPresent()) return "No existe una librería con ese id";
        Optional<Edition> edition = editionRepository.findById(editionId);
        // Verify if are any edition on the optional
        if (!edition.isPresent()) return "No existe una edición con ese id";
        libraryRepository.associateEdition(edition.get(), library.get());
        return "Se ha asociado exitosamente";
    }

    /**
     * Manages the disassociate method with an {@link EntityManager}
     *
     * @param libraryId, the library id
     * @param editionId, the edition id
     * @return a result message
     */
    public String disassociateEdition(Integer libraryId, Integer editionId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);
        editionRepository = new EditionRepositoryImpl(entityManager);
        Optional<Library> library = libraryRepository.findById(libraryId);
        //Verify the library is exists
        if (!library.isPresent()) return "No existe una librería con ese id";
        Optional<Edition> edition = editionRepository.findById(editionId);
        // Verify if are any edition on the optional
        if (!edition.isPresent()) return "No existe una edición con ese id";
        libraryRepository.disassociateEdition(edition.get(), library.get());
        return "Se ha desasociado exitosamente";
    }

    public List<AssociationPOJO> listAssociations() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);

        List<Library> libraries = libraryRepository.findAll();
        List<AssociationPOJO> associations = new ArrayList<>();

        for (Library library : libraries)
            for (Edition edition : library.getEditions())
                associations.add(new AssociationPOJO(library.getLibraryId(), edition.getEditionId()));

        return associations;
    }

}
