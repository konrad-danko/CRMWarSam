package pl.coderslab.model;

import java.math.BigDecimal;

public class Report {

    private String employeeName;
    private BigDecimal manHours;
    private BigDecimal repairCost;

    public Report(String employeeName, BigDecimal manHours, BigDecimal repairCost) {
        this.employeeName = employeeName;
        this.manHours = manHours;
        this.repairCost = repairCost;
    }
    public Report() {
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getManHours() {
        return manHours;
    }
    public void setManHours(BigDecimal manHours) {
        this.manHours = manHours;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }
    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }
}
