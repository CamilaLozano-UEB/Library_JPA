package edu.unbosque.jpa.repositories;


import edu.unbosque.jpa.entities.Author;
import edu.unbosque.jpa.entities.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {

    private EntityManager entityManager;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Book> findById(Integer id) {
        Book book = entityManager.find(Book.class, id);
        return book != null ? Optional.of(book) : Optional.empty();
    }

    /*public Optional<Book> findByNameNamedQuery(String title) {
        Book book = entityManager.createNamedQuery("Book.findByTitle", Book.class)
                .setParameter("title", title)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }*/

    public List<Book> findAll() {
        return entityManager.createQuery("from Book").getResultList();
    }

    public Optional<Book> save(Book book) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            return Optional.of(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public String modify(Integer bookId, Integer authorId, String title, String isbnNumber, String genre) {
        entityManager.getTransaction().begin();
        Optional<Book> book = this.findById(bookId);
        if (!book.isPresent()) return "El id del libro no existe!";

        Author author = (Author) entityManager.createQuery("SELECT a FROM Author a WHERE a.id = :id").
                setParameter("id", authorId).getSingleResult();

        if (author == null) return "El id del libro no existe!";

        book.get().setAuthor(author);
        book.get().setTitle(title);
        book.get().setIsbn(isbnNumber);
        book.get().setGenre(genre);

        entityManager.getTransaction().commit();

        return "Se ha modificado exitosamente!";
    }

    @Override
    public void delete(Integer bookId) {
        entityManager.getTransaction().begin();
        Optional<Book> book = this.findById(bookId);
        book.ifPresent(book1 -> {
            entityManager.remove(book1);
            entityManager.getTransaction().commit();

        });
    }


}
