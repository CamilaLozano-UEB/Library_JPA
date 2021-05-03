package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository {

    List<Library> findAll();

    Optional<Library> save(Library library);

}
