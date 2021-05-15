package edu.unbosque.servlets.pojos;

import java.util.Date;

public class RentPOJO {

    private Integer rentId;
    private String customer;
    private int edition;
    private Date renting_date;

    /**
     *
     * @param rentId the rent id
     * @param customer_email the email of the customer of the rent
     * @param edition_id the edition id of the rent
     * @param renting_date Date of the rent
     */
    public RentPOJO(Integer rentId, String customer_email, int edition_id, Date renting_date) {
        this.rentId = rentId;
        this.customer = customer_email;
        this.edition = edition_id;
        this.renting_date = renting_date;
    }

    /**
     *
     * @return the rent id
     */
    public Integer getRentId() {
        return rentId;
    }

    /**
     *
     * @param rentId the rent id
     */
    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    /**
     *
     * @return the edition id of the rent
     */
    public int getEdition_id() {
        return edition;
    }

    /**
     *
     * @param edition_id the edition id of the rent
     */
    public void setEdition_id(int edition_id) {
        this.edition = edition_id;
    }

    /**
     *
     * @return the email of the customer of the rent
     */
    public String getCustomer_email() {
        return customer;
    }

    /**
     *
     * @param customer_email the email of the customer of the rent
     */
    public void setCustomer_email(String customer_email) {
        this.customer = customer_email;
    }

    /**
     *
     * @return Date of the rent
     */
    public Date getRenting_date() {
        return renting_date;
    }

    /**
     *
     * @param renting_date Date of the rent
     */
    public void setRenting_date(Date renting_date) {
        this.renting_date = renting_date;
    }
}