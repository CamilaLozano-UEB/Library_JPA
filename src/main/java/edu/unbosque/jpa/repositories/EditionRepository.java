package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Edition;

import java.util.List;
import java.util.Optional;

public interface EditionRepository {
    Optional<Edition> findById(Integer id);

    List<Edition> findAll();

    Optional<Edition> save(Edition edition);

    void modify(Integer id, String name, String country);

    void delete(Integer id);
}
