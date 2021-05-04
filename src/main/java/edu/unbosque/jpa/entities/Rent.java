package edu.unbosque.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "Rent") // Optional
@NamedQueries({
        @NamedQuery(name = "Rent.findByRenting_date",
                query = "SELECT r FROM Rent r WHERE r.renting_date = :renting_date"),
        //select * from rent where renting_date between '2017/05/07' and '2017/05/08' (rango de dias)
        @NamedQuery(name = "Rent.findAll",
                query = "SELECT r FROM Rent r")
})
public class Rent {

    @Id
    @GeneratedValue
    @Column(name = "rent_id")
    private Integer rentId;

    //@ManyToOne
    @JoinColumn(name = "user_id")
    private String user_id;
    // private User user_id;

    @Column(name = "renting_date")
    private String renting_date;

    @ManyToOne
    @JoinColumn(name = "edition_id")
    private Edition edition_id;

    public Rent() {}

    public Rent(String renting_date) {
        this.renting_date = renting_date;
    }

    public Rent(Integer rentId, String renting_date) {
        this.rentId = rentId;
        this.renting_date = renting_date;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public String getRenting_date() {
        return renting_date;
    }

    public void setRenting_date(String renting_date) {
        this.renting_date = renting_date;
    }

    public Edition getEdition_id() {
        return edition_id;
    }

    public void setEdition_id(Edition edition_id) {
        this.edition_id = edition_id;
    }

//    public User getUser_id() {
//        return user_id;
//    }

//    public void setUser_id(User user_id) {
//        this.user_id = user_id;
//    }



}
