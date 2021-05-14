package edu.unbosque.jpa.repositories;


import edu.unbosque.jpa.entities.Library;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class LibraryRepositoryImpl implements LibraryRepository {

    private EntityManager entityManager;

    public LibraryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Library> findAll() {
        return entityManager.createQuery("from Library").getResultList();
    }

    @Override
    public Optional<Library> findById(Integer libraryId) {
        Library library = entityManager.find(Library.class, libraryId);
        return library != null ? Optional.of(library) : Optional.empty();
    }

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

    @Override
    public String modify(Integer libraryId, String name) {
        entityManager.getTransaction().begin();
        Optional <Library> library = this.findById(libraryId);
        if(!library.isPresent()) return "No existe la libreria con el id ingresado!";
        library.get().setName(name);
        entityManager.getTransaction().commit();
        return "Se ha modificado exitosamente!";
    }

    @Override
    public void delete(Integer libraryId) {
        entityManager.getTransaction().begin();
        Library library = this.findById(libraryId).get();
        if (library == null) return;
        entityManager.remove(library);
        entityManager.getTransaction().commit();
    }

}
