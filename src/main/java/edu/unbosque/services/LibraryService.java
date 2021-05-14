package edu.unbosque.services;


import edu.unbosque.jpa.entities.Library;
import edu.unbosque.jpa.repositories.LibraryRepository;
import edu.unbosque.jpa.repositories.LibraryRepositoryImpl;
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

    public List<LibraryPOJO> listLibraries() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        libraryRepository = new LibraryRepositoryImpl(entityManager);
        List<Library> libraries = libraryRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<LibraryPOJO> librariesPOJO = new ArrayList<>();
        for (Library library : libraries) {
            librariesPOJO.add(new LibraryPOJO(
                    library.getLibraryId(),
                    library.getName()
            ));
        }

        return librariesPOJO;

    }

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

    public String modifyLibrary(Integer libraryId, String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);
        String message = libraryRepository.modify(libraryId, name);
        entityManager.close();
        entityManagerFactory.close();
        return message;
    }

    public String deleteLibrary(Integer libraryId){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libraryRepository = new LibraryRepositoryImpl(entityManager);
        Optional<Library> library = libraryRepository.findById(libraryId);
        if (!library.isPresent()) return "No existe la biblioteca con el id ingresado!";
        libraryRepository.delete(libraryId);
        entityManager.close();
        entityManagerFactory.close();
        return "Se ha eliminado la biblioteca exitosamente!";


    }

}
