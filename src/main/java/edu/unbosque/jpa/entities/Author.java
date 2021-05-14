package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Annotations to configure the entity, give a name and define the named queries
 */
@Entity
@Table(name = "Author")
@NamedQueries({
        @NamedQuery(name = "Author.findByName",
                query = "SELECT a FROM Author a WHERE a.name = :name"),
        @NamedQuery(name = "Author.modify",
                query = "UPDATE Author a SET a.name= :name, a.country= :country WHERE a.id = :id"),
        @NamedQuery(name = "Author.remove",
                query = "DELETE FROM Author a WHERE a.id = :id")
})
/**
 * Author Entity
 */
public class Author {

    /**
     * Define the attributes for the Author entity, the Id and the relations
     */
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

    /**
     * Constructor for the creation of a new entity of type Author
     *
     * @param name    the Author name
     * @param country the Author country
     */
    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * General constructor
     *
     * @param authorId the Author Id
     * @param name     the Author name
     * @param country  the Author country
     */
    public Author(Integer authorId, String name, String country) {
        this.authorId = authorId;
        this.name = name;
        this.country = country;
    }

    /**
     * @return the AuthorId
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId, the new AuthorId
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * @return the AuthorName
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the new Author name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Authors Books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * @return the Author Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the new Country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Add a book to the books List
     *
     * @param book a new book for the Author List
     */
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    /**
     * Removes a determinate Book from the Author books List
     *
     * @param book the book to remove
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

}
