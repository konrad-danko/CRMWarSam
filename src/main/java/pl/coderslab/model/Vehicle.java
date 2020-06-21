package pl.coderslab.model;

import java.sql.Date;

public class Vehicle {

    private int vehicleId;
    private String mark;
    private String model;
    private int prodYear;
    private String regNumber;
    private Date nextInspDate;
    private int customerId;
    private String custName;

    public Vehicle() {
    }
    public Vehicle(String mark, String model, int prodYear, String regNumber, Date nextInspDate, int customerId) {
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.regNumber = regNumber;
        this.nextInspDate = nextInspDate;
        this.customerId = customerId;
    }
    public Vehicle(String mark, String model, int prodYear, String regNumber, Date nextInspDate, int customerId, String custName) {
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.regNumber = regNumber;
        this.nextInspDate = nextInspDate;
        this.customerId = customerId;
        this.custName = custName;
    }
    public Vehicle(int vehicleId, String mark, String model, int prodYear, String regNumber, Date nextInspDate, int customerId, String custName) {
        this.vehicleId = vehicleId;
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.regNumber = regNumber;
        this.nextInspDate = nextInspDate;
        this.customerId = customerId;
        this.custName = custName;
    }
    public Vehicle(int vehicleId, String mark, String model, int prodYear, String regNumber, Date nextInspDate, int customerId) {
        this.vehicleId = vehicleId;
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.regNumber = regNumber;
        this.nextInspDate = nextInspDate;
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public int getProdYear() {
        return prodYear;
    }
    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    public String getRegNumber() {
        return regNumber;
    }
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Date getNextInspDate() {
        return nextInspDate;
    }
    public void setNextInspDate(Date nextInspDate) {
        this.nextInspDate = nextInspDate;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
}
