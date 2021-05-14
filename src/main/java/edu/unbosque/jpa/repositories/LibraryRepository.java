package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Edition;
import edu.unbosque.jpa.entities.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository {

    List<Library> findAll();

    Optional<Library> findById(Integer libraryId);

    String save(Library library);

    String modify(Integer libraryId, String name);

    void delete(Integer libraryId);

    void associateEdition(Edition edition, Library library);

    void disassociateEdition(Edition edition, Library library);

}
