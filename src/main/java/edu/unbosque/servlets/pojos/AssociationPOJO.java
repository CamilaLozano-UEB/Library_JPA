package edu.unbosque.servlets.pojos;

public class AssociationPOJO {

    private Integer libraryId;
    private Integer editionId;

    /**
     * @param libraryId the library id
     * @param editionId the edition id
     */
    public AssociationPOJO(Integer libraryId, Integer editionId) {
        this.libraryId = libraryId;
        this.editionId = editionId;
    }

    /**
     * @return the library id
     */
    public Integer getLibraryId() {
        return libraryId;
    }

    /**
     * @param libraryId the new library id
     */
    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
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
}
