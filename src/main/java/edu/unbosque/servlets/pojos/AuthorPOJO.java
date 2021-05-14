package edu.unbosque.servlets.pojos;

public class AuthorPOJO {

    private Integer authorId;

    private String name;

    private Integer numBooks;

    private String country;

    /**
     * @param authorId the author id
     * @param name     the author name
     * @param numBooks the num of books of the author
     * @param country  the author country
     */
    public AuthorPOJO(Integer authorId, String name, Integer numBooks, String country) {
        this.authorId = authorId;
        this.name = name;
        this.numBooks = numBooks;
        this.country = country;
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

    /**
     * @return the author name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the new author name
     */
    public void setName(String name) {
        this.name = name;
    }
}
