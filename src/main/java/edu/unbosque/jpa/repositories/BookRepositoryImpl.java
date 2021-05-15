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

    /**
     * Finds the books of an specific Author
     *
     * @param author the author
     * @return a list of books
     */
    @Override
    public List<Book> findAuthorBooks(Author author) {
        return entityManager.createQuery("SELECT  b FROM Book b WHERE b.author = :author")
                .setParameter("author", author).getResultList();
    }

    /**
     * Find a Book by id
     *
     * @param id the book id to be searched book
     */
    @Override
    public Optional<Book> findById(Integer id) {
        Book book = entityManager.find(Book.class, id);
        return book != null ? Optional.of(book) : Optional.empty();
    }

    /**
     * Finds all the books of the DB
     *
     * @return a list of books
     */
    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("from Book").getResultList();
    }

    /**
     * Saves a new book to the DB
     *
     * @param book the book to be saved
     * @return a message of the result
     */
    @Override
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

    /**
     * Modify the attributes of an specific book
     *
     * @param bookId     the id of the book to be modified
     * @param authorId   the new author id
     * @param title      the new title
     * @param isbnNumber the new ISBN number
     * @param genre      the new genre
     * @return a message of the result
     */
    @Override
    public String modify(Integer bookId, Integer authorId, String title, String isbnNumber, String genre) {
        entityManager.getTransaction().begin();
        Optional<Book> book = this.findById(bookId);
        if (!book.isPresent()) return "El id del libro no existe!";

        Author author = (Author) entityManager.createQuery("SELECT a FROM Author a WHERE a.id = :id").
                setParameter("id", authorId).getSingleResult();

        if (author == null) return "El id del autor no existe!";

        book.get().setAuthor(author);
        book.get().setTitle(title);
        book.get().setIsbn(isbnNumber);
        book.get().setGenre(genre);

        entityManager.getTransaction().commit();

        return "Se ha modificado exitosamente!";
    }

    /**
     * Delete a Book from th DB
     *
     * @param bookId the id of the book to be deleted
     */
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
