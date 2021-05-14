package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Creation of table and Queries for the database
 */
@Entity
@Table(name = "Library") // Optional
public class Library {
    /**
     * Create of the columns for the costumer table and the relations
     */
    @Id
    @GeneratedValue
    @Column(name = "library_id")
    private Integer libraryId;

    @Column(name = "name")
    private String name;

    // FetchType.EAGER: When we retrieve a Library, we'll also automatically retrieve all of its corresponding Editions
    // CascadeType.PERSIST: When we save a superhero, its movies will also be saved
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Library_Edition",
            joinColumns = {@JoinColumn(name = "library_id")},
            inverseJoinColumns = {@JoinColumn(name = "edition_id")}
    )
    private Set<Edition> editions = new HashSet<>();

    /**
     * Void constructor for the library class
     */
    public Library() {
    }

    /**
     * Constructor for the library class without principal key libraryId
     *
     * @param libraryId, id of library
     * @param name,      name of library
     */
    public Library(Integer libraryId, String name) {
        this.libraryId = libraryId;
        this.name = name;
    }

    /**
     * Constructor for the creation of a new entity of type library
     *
     * @param name, name of library
     */
    public Library(String name) {
        this.name = name;
    }

    /**
     * Method for add an edition to a library
     *
     * @param edition, edition entity
     */
    public void addEdition(Edition edition) {
        this.editions.add(edition);
        edition.addLibrary(this);
    }

    /**
     * Method for remove an edition to a library
     *
     * @param edition, edition entity
     */
    public void removeEdition(Edition edition) {
        this.editions.remove(edition);
        edition.removeLibrary(this);
    }

    /**
     * Getter for libraryId
     *
     * @return the library id
     */
    public Integer getLibraryId() {
        return libraryId;
    }

    /**
     * Getter for name
     *
     * @return the library name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name, the new library name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for Edition hash
     *
     * @return the edition hash
     */
    public Set<Edition> getEditions() {
        return editions;
    }
}
