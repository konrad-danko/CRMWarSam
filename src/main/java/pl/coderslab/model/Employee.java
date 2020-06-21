package pl.coderslab.model;

import java.math.BigDecimal;

public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String note;
    private BigDecimal hourlyWage;

    public Employee() {
    }
    public Employee(String firstName, String lastName, String address, String phone, String note, BigDecimal hourlyWage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.hourlyWage = hourlyWage;
    }
    public Employee(int employeeId, String firstName, String lastName, String address, String phone, String note, BigDecimal hourlyWage) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.hourlyWage = hourlyWage;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }
    public void setHourlyWage(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
