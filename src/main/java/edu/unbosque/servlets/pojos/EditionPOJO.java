package edu.unbosque.servlets.pojos;

import edu.unbosque.jpa.entities.Book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

public class EditionPOJO {

    private Integer editionId;
    private String description;
    private Date releaseYear;
    private Book book;

    public EditionPOJO(Integer editionId, String description, Date releaseYear, Book book) {
        this.editionId = editionId;
        this.description = description;
        this.releaseYear = releaseYear;
        this.book = book;
    }

    public Integer getEditionId() {
        return editionId;
    }

    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
