package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.*;

/**
 * Annotations to configure the entity, give a name and define the named queries
 */
@Entity
@Table(name = "Edition") // Optional
@NamedQueries({
        @NamedQuery(name = "Edition.findAll",
                query = "SELECT b FROM Edition b")
})
/**
 * Edition Entity
 */
public class Edition {

    /**
     * Define the attributes for the Edition entity, the Id and the relations
     */
    @Id
    @GeneratedValue
    @Column(name = "edition_id")
    private Integer editionId;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Date releaseYear;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    // FetchType.EAGER: When we retrieve a Library, we'll also automatically retrieve all of its corresponding Editions
    // CascadeType.PERSIST: When we save a superhero, its movies will also be saved

    @ManyToMany(mappedBy = "editions", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Library> libraries = new HashSet<>();

    @OneToMany(mappedBy = "edition", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rent> rents = new ArrayList<>();

    public Edition() {
    }

    /**
     * @param book        the Edition book (FK)
     * @param description the Edition description
     * @param releaseYear the releaseYear of the Edition
     */
    public Edition(Book book, String description, Date releaseYear) {
        this.book = book;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    /**
     * @return the EditionId
     */
    public Integer getEditionId() {
        return editionId;
    }

    /**
     * @param editionId the new EditionId
     */
    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    /**
     * @return the Edition description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the Edtiion Id
     */
    public Date getReleaseYear() {
        return releaseYear;
    }

    /**
     * @param releaseYear the new release year
     */
    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return the book of the Edition
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the new book of the edition
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the libraries associated with this Edition
     */
    public Set<Library> getLibraries() {
        return libraries;
    }

    /**
     * add a new Library associated wih this Edition
     *
     * @param library the new Library
     */
    public void addLibrary(Library library) {
        libraries.add(library);
    }

    /**
     * removes a Library associated with this Edition
     *
     * @param library the Library to be removed
     */
    public void removeLibrary(Library library) {
        libraries.remove(library);
    }

    /**
     * @return a List of the rents of this Edition
     */
    public List<Rent> getRents() {
        return rents;
    }

    /**
     * Add´s a new rent to the rents Set
     *
     * @param rent a new rent
     */
    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setEdition(this);
    }
    /**
     * Removes a determinate Rent from the Edition rents List
     *
     * @param rent the rent to remove
     */
    public void removeRents( Rent rent){
        rents.remove(rent);
    }

}
