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

    public Rent() {}

    public Rent(Date renting_date) {
        this.renting_date = renting_date;
    }


    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public Date getRenting_date() {
        return renting_date;
    }

    public void setRenting_date(Date renting_date) {
        this.renting_date = renting_date;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
