package edu.unbosque.jpa.entities;

import javax.persistence.*;

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
public class Book {

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

    @OneToOne(mappedBy = "book")
    private Edition edition;

    @Column(name = "genre")
    private String genre;

    public Book() {
    }

    public Book(String title, String isbn, String genre) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
    }

    public Book(Integer bookId, String title, String isbn, String genre) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Edition getEdition() {
        return edition;
    }

    public void addEdition(Edition edition) {
        this.edition = edition;
        edition.setBook(this);
    }

}
