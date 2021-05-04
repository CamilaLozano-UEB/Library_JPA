package edu.unbosque.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users") // Optional
@NamedQueries({
        @NamedQuery(name = "Users.findByEmail",
                query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name = "Users.findByFirst_name",
                query = "SELECT u FROM User u WHERE u.first_name = :first_name"),
        @NamedQuery(name = "Users.findByLast_name",
                query = "SELECT u FROM User u WHERE u.last_name = :last_name"),
        @NamedQuery(name = "Users.findAll",
                query = "SELECT u FROM User u")
})
public class User {
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
    private String age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rent> rents = new ArrayList<>();

    public User() {
    }

    public User(String first_name, String last_name, String gender, String age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
    }

    public User(String email, String first_name, String last_name, String gender, String age) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setUser(this);
    }
}
