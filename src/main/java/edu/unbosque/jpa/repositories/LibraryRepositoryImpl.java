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
    public Optional<Library> save(Library library) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(library);
            entityManager.getTransaction().commit();
            return Optional.of(library);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void modify(Integer libraryId, String name) {
        entityManager.getTransaction().begin();
        Library library = this.findById(libraryId).get();
        if (library == null) return;
        library.setName(name);
        entityManager.getTransaction().commit();
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
