package pl.coderslab.model;

import java.sql.Date;

public class TestClass {
    private int customerId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phone;
    private String email;

    public TestClass() {
    }

    public TestClass(int customerId, String firstName, String lastName, Date birthDate, String phone, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
    }
}
