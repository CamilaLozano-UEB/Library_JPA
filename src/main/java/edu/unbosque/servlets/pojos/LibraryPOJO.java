package edu.unbosque.servlets.pojos;

public class LibraryPOJO {

    private Integer libraryId;

    private String name;

    /**
     * @param libraryId, the library id
     * @param name,      the library name
     */
    public LibraryPOJO(Integer libraryId, String name) {
        this.libraryId = libraryId;
        this.name = name;
    }

    /**
     * @return the library name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name,the new library name
     */
    public void setName(String name) {
        this.name = name;
    }

}
