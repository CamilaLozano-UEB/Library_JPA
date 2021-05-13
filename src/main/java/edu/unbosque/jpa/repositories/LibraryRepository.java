package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Author;
import edu.unbosque.jpa.entities.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository {

    List<Library> findAll();

    Optional<Library> findById(Integer libraryId);

    Optional<Library> save(Library library);

    void modify(Integer libraryId, String name);

    void delete(Integer libraryId);

}
