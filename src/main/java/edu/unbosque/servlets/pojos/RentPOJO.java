package edu.unbosque.servlets.pojos;

import java.util.Date;

public class RentPOJO {

    private Integer rentId;
    private String customer;
    private int edition;
    private Date renting_date;

    public RentPOJO(Integer rentId, String customer_email, int edition_id, Date renting_date) {
        this.rentId = rentId;
        this.customer = customer_email;
        this.edition = edition_id;
        this.renting_date = renting_date;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public int getEdition_id() {
        return edition;
    }

    public void setEdition_id(int edition_id) {
        this.edition = edition_id;
    }

    public String getCustomer_email() {
        return customer;
    }

    public void setCustomer_email(String customer_email) {
        this.customer = customer_email;
    }

    public Date getRenting_date() {
        return renting_date;
    }

    public void setRenting_date(Date renting_date) {
        this.renting_date = renting_date;
    }
}