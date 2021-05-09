package edu.unbosque.jpa.repositories;


import edu.unbosque.jpa.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(Integer id);

    List<Book> findAll();

    Optional<Book> save(Book book);

    void modify(Integer bookId, Integer authorId, String title, String isbnNumber, String genre);

    void delete(Integer bookId);
}
