package edu.unbosque.servlets.pojos;

import java.util.Date;

public class EditionPOJO {

    private Integer editionId;
    private String description;
    private Date releaseYear;
    private Integer bookId;
    private String bookTitle;

    public EditionPOJO(Integer editionId, String description, Date releaseYear, Integer bookId, String bookTitle) {
        this.editionId = editionId;
        this.description = description;
        this.releaseYear = releaseYear;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}