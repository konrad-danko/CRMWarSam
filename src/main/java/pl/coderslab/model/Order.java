package pl.coderslab.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {

    private int orderId;            //ORDER_ID SMALLINT
    private int vehicleId;          //VEHICLE_ID SMALLINT,-- Pojazd którego dotyczy naprawa
    private String vehicleName;
    private String problemDescr;    //PROBLEM_DESCR VARCHAR(255),-- Opis problemu
    private int employeeId;         //EMPLOYEE_ID SMALLINT, -- Przypisany do naprawy pracownik
    private String employeeName;
    private Date ordrCreatDate;     //ORDR_CREAT_DATE DATE,-- Data przyjęcia do naprawy
    private Date planRepDate;       //PLAN_REP_DATE DATE,-- Planowana data rozpoczęcia naprawy
    private Date actuRepDate;       //ACTU_REP_DATE DATE,-- Data rozpoczęcia naprawy
    private BigDecimal manHours;    //MAN_HOURS DECIMAL(6,2),-- Liczba roboczogodzin
    private BigDecimal hourlyWage;  //HOURLY_WAGE DECIMAL(6,2),-- Koszt roboczogodziny
    private BigDecimal partsCost;   //PARTS_COST DECIMAL(10,2), -- Koszt wykorzystanych części
    private BigDecimal repairCost;  //REPAIR_COST DECIMAL(10,2),-- Koszt naprawy dla klienta
    private String repairDescr;     //REPAIR_DESCR VARCHAR(500),-- Opis naprawy
    private int statId;             //STAT_ID INT, -- Status
    private String statName;

    public Order() {}
    public Order(int vehicleId, String problemDescr, int employeeId, Date ordrCreatDate, Date planRepDate, Date actuRepDate, BigDecimal manHours, BigDecimal hourlyWage, BigDecimal partsCost, BigDecimal repairCost, String repairDescr, int statId) {
        this.vehicleId = vehicleId;
        this.problemDescr = problemDescr;
        this.employeeId = employeeId;
        this.ordrCreatDate = ordrCreatDate;
        this.planRepDate = planRepDate;
        this.actuRepDate = actuRepDate;
        this.manHours = manHours;
        this.hourlyWage = hourlyWage;
        this.partsCost = partsCost;
        this.repairCost = repairCost;
        this.repairDescr = repairDescr;
        this.statId = statId;
    }
    public Order(int orderId, int vehicleId, String problemDescr, int employeeId, Date ordrCreatDate, Date planRepDate, Date actuRepDate, BigDecimal manHours, BigDecimal hourlyWage, BigDecimal partsCost, BigDecimal repairCost, String repairDescr, int statId) {
        this.orderId = orderId;
        this.vehicleId = vehicleId;
        this.problemDescr = problemDescr;
        this.employeeId = employeeId;
        this.ordrCreatDate = ordrCreatDate;
        this.planRepDate = planRepDate;
        this.actuRepDate = actuRepDate;
        this.manHours = manHours;
        this.hourlyWage = hourlyWage;
        this.partsCost = partsCost;
        this.repairCost = repairCost;
        this.repairDescr = repairDescr;
        this.statId = statId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getProblemDescr() {
        return problemDescr;
    }
    public void setProblemDescr(String problemDescr) {
        this.problemDescr = problemDescr;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getOrdrCreatDate() {
        return ordrCreatDate;
    }
    public void setOrdrCreatDate(Date ordrCreatDate) {
        this.ordrCreatDate = ordrCreatDate;
    }

    public Date getPlanRepDate() {
        return planRepDate;
    }
    public void setPlanRepDate(Date planRepDate) {
        this.planRepDate = planRepDate;
    }

    public Date getActuRepDate() {
        return actuRepDate;
    }
    public void setActuRepDate(Date actuRepDate) {
        this.actuRepDate = actuRepDate;
    }

    public BigDecimal getManHours() {
        return manHours;
    }
    public void setManHours(BigDecimal manHours) {
        this.manHours = manHours;
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }
    public void setHourlyWage(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public BigDecimal getPartsCost() {
        return partsCost;
    }
    public void setPartsCost(BigDecimal partsCost) {
        this.partsCost = partsCost;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }
    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    public String getRepairDescr() {
        return repairDescr;
    }
    public void setRepairDescr(String repairDescr) {
        this.repairDescr = repairDescr;
    }

    public int getStatId() {
        return statId;
    }
    public void setStatId(int statId) {
        this.statId = statId;
    }

    public String getStatName() {
        return statName;
    }
    public void setStatName(String statName) {
        this.statName = statName;
    }
}

