package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Book;
import edu.unbosque.jpa.entities.Edition;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EditionRepository {
    Optional<Edition> findById(Integer id);

    List<Edition> findAll();

    Optional<Edition> save(Edition edition);

    void modify(Integer editionId, Book book, String description,  Date releaseYear);

    void delete(Integer id);
}
