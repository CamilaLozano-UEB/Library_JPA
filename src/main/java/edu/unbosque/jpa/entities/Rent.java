package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Rent") // Optional
@NamedQueries({
        @NamedQuery(name = "Rent.findByRenting_date",
                query = "SELECT r FROM Rent r WHERE r.renting_date  BETWEEN : renting_date  AND : renting_date "),

        @NamedQuery(name = "Rent.findAll",
                query = "SELECT r FROM Rent r")
})
public class Rent {

    @Id
    @GeneratedValue
    @Column(name = "rent_id")
    private Integer rentId;

    @ManyToOne
    @JoinColumn(name = "email")
    private Customer customer;

    @Column(name = "renting_date")
    private Date renting_date;

    @ManyToOne
    @JoinColumn(name = "edition_id")
    private Edition edition;
    /**
     * constructor for the rent class
     */
    public Rent() {}

    /**
     * Constructor for the rent class without edition,customer and principal key rent
     *
     * @param renting_date, Date of the rent
     */
    public Rent(Date renting_date) {
        this.renting_date = renting_date;
    }

    /**
     * Getter for rentId
     *
     * @return the id rent
     */
    public Integer getRentId() {
        return rentId;
    }

    /**
     * Getter for reting_date
     *
     * @return the date of the rent
     */
    public Date getRenting_date() {
        return renting_date;
    }

    /**
     * Getter for edition
     *
     * @return the edition of the rent
     */
    public Edition getEdition() {
        return edition;
    }
    /**
     * Setter for edition
     *
     * @param edition, the edition of the rent
     */
    public void setEdition(Edition edition) {
        this.edition = edition;
    }
    /**
     * Getter for customer
     *
     * @return the customer of the rent
     */
    public Customer getCustomer() {
        return customer;
    }
    /**
     * Setter for customer
     *
     * @param customer, the customer of the rent
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
