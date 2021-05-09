package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author") // Optional
@NamedQueries({
        @NamedQuery(name = "Author.findByName",
                query = "SELECT a FROM Author a WHERE a.name = :name"),
        @NamedQuery(name = "Author.modify",
                query = "UPDATE Author a SET a.name= :name, a.country= :country WHERE a.id = :id"),
        @NamedQuery(name = "Author.remove",
                query = "DELETE FROM Author a WHERE a.id = :id")
})
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private Integer authorId;

    @Column(nullable = false)
    private String name;

    @Column
    private String country;

    // FetchType.EAGER: When we retrieve a Library, we'll also automatically retrieve all of its corresponding Editions
    // CascadeType.ALL: Propagates all operations from Author to Books
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Author(Integer authorId, String name, String country) {
        this.authorId = authorId;
        this.name = name;
        this.country = country;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }
    public void removeBook(Book book){
        books.remove(book);
    }

}
