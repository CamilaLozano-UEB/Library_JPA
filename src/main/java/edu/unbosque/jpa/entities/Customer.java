package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creation of table and Queries for the database
 */
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

    /**
     * constructor for the customer class
     */
    public Customer() {
    }

    /**
     * Constructor for the customer class without principal key email
     *
     * @param first_name, name of the customer
     * @param last_name,  last name of the customer
     * @param gender,     gender of the customer
     * @param age,        age of the customer
     */
    public Customer(String first_name, String last_name, String gender, Integer age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Constructor with al parameters and principal key for the database
     *
     * @param email,      id of the customer
     * @param first_name, name of the customer
     * @param last_name,  last name of the customer
     * @param gender,     gender of the customer
     * @param age,        age of the customer
     */
    public Customer(String email, String first_name, String last_name, String gender, Integer age) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Getter for email
     *
     * @return the id customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email, the new id customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for first_name
     *
     * @return the customer first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Setter for first_name
     *
     * @param first_name, the new customer first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Getter for last_name
     *
     * @return the customer last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Setter for last_name
     *
     * @param last_name, the new customer last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Getter for gender
     *
     * @return the customer gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter for gender
     *
     * @param gender, the new customer gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter for age
     *
     * @return the customer age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter for age
     *
     * @param age, the new customer age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Save the rents for each customer
     *
     * @param rent, entity rent
     */
    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setCustomer(this);
    }

}
