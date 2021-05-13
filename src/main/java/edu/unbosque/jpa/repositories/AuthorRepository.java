package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Author;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> findById(Integer id);

    List<Author> findAll();

    String save(Author author);

    String modify(Integer id, String name, String country);

    void delete(Author author);

}
