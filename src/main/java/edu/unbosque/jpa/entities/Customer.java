package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer") // Optional
@NamedQueries({
        @NamedQuery(name = "Customer.findByEmail",
                query = "SELECT c FROM Customer  c WHERE c.email = :email"),
        @NamedQuery(name = "Customer.findByFirst_name",
                query = "SELECT c FROM Customer c WHERE c.first_name = :first_name"),
        @NamedQuery(name = "Customer.findByLast_name",
                query = "SELECT c FROM Customer c WHERE c.last_name = :last_name"),
        @NamedQuery(name = "Customer.findAll",
                query = "SELECT c FROM Customer c")
})
public class Customer {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rent> rents = new ArrayList<>();

    public Customer() {
    }

    public Customer(String first_name, String last_name, String gender, Integer age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
    }

    public Customer(String email, String first_name, String last_name, String gender, Integer age) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setCustomer(this);
    }

}
