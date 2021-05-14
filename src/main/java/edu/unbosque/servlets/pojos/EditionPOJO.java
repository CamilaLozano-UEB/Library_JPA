package edu.unbosque.servlets.pojos;

import java.util.Date;

public class EditionPOJO {

    private Integer editionId;
    private String description;
    private Date releaseYear;
    private Integer bookId;
    private String bookTitle;

    /**
     * @param editionId   the edition id
     * @param description the edition description
     * @param releaseYear the edition release year
     * @param bookId      the edition book id
     * @param bookTitle   the edition book title
     */
    public EditionPOJO(Integer editionId, String description, Date releaseYear, Integer bookId, String bookTitle) {
        this.editionId = editionId;
        this.description = description;
        this.releaseYear = releaseYear;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    /**
     * @return the edition id
     */
    public Integer getEditionId() {
        return editionId;
    }

    /**
     * @param editionId the new edition id
     */
    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    /**
     * @return the edition release year
     */
    public Date getReleaseYear() {
        return releaseYear;
    }

    /**
     * @param releaseYear new edition release year
     */
    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return the edition book id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId the new edition book id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}