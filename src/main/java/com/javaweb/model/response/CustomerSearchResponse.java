package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;

public class CustomerSearchResponse extends AbstractDTO {
    private Long id;
    private String fullname;
    private String phone;
    private String demand;
    private String status;
    private String companyName;
    private String email;
    private int isActive;

    public int isActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
