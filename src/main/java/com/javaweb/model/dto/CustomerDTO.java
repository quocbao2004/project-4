package com.javaweb.model.dto;

public class CustomerDTO extends AbstractDTO{
    private String fullname;
    private String managementStaff;
    private String phone;
    private String email;
    private String demand;
    private String status;
    private String companyname;
    private int isActive;

    public int isActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getManagementStaff() {
        return managementStaff;
    }

    public void setManagementStaff(String managementStaff) {
        this.managementStaff = managementStaff;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
