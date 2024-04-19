package com.javaweb.model.dto;

import java.util.List;

public class AssignmentCustomerDTO {
    private Long customerId;
    private List<Long> Staffs;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getStaffs() {
        return Staffs;
    }

    public void setStaffs(List<Long> staffs) {
        Staffs = staffs;
    }
}
