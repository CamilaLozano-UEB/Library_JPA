package edu.unbosque.servlets.pojos;

public class CustomerPOJO {
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private Integer age;

    /**
     *
     * @param email, the customer id
     * @param first_name, the customer name
     * @param last_name, the customer last name
     * @param gender, the customer gender
     * @param age,, the customer age
     */
    public CustomerPOJO(String email, String first_name, String last_name, String gender, Integer age) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
    }

    /**
     *
     * @return the customer id
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email the new customer id
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
