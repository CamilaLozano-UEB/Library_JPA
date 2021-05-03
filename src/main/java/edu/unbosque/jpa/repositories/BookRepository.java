package edu.unbosque.jpa.repositories;


import edu.unbosque.jpa.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(Integer id);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByNameNamedQuery(String title);

    List<Book> findAll();

    Optional<Book> save(Book book);

}
