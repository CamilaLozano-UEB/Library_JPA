package edu.unbosque.servlets.pojos;

public class BookPOJO {

    private Integer bookId;
    private String title;
    private String isbn;
    private Integer authorId;
    private String authorName;
    private String genre;

    /**
     * @param bookId     the book id
     * @param title      the book title
     * @param isbn       the book ISBN
     * @param authorId   the author id of the book
     * @param authorName the author name
     * @param genre      the book genre
     */
    public BookPOJO(Integer bookId, String title, String isbn, Integer authorId, String authorName, String genre) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.authorId = authorId;
        this.authorName = authorName;
        this.genre = genre;
    }

    /**
     * @return the book id
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
     * @return the book genre
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the new author id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

}