package edu.unbosque.jpa.entities;

import javax.persistence.*;

/**
 * Annotations to configure the entity, give a name and define the named queries
 */
@Entity
@Table(name = "Book") // Optional
@NamedQueries({
        @NamedQuery(name = "Book.findById",
                query = "SELECT b FROM Book b WHERE b.id = :id"),
        @NamedQuery(name = "Book.findAll",
                query = "SELECT b FROM Book b"),
        @NamedQuery(name = "Book.modify",
                query = "UPDATE Book b SET b.author= :author, b.title= :tittle, b.isbn= :isbn, b.genre= :genre WHERE b.id = :id"),
        @NamedQuery(name = "Book.delete",
                query = "DELETE FROM Book b WHERE b.id = :id")
})
/**
 * Book Entity
 */
public class Book {

    /**
     * Define the attributes for the Book entity, the Id and the relations
     */
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Integer bookId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "isbn_number")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "genre")
    private String genre;

    /**
     * @param title the book title
     * @param isbn  the book ISBN
     * @param genre the book genre
     */
    public Book(String title, String isbn, String genre) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
    }

    public Book() {

    }

    /**
     * @return th book id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId the new book id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the new book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the book ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the new isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the author of the book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the new author of the book
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the new genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

}
