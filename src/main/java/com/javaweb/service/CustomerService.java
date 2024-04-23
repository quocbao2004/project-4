package com.javaweb.service;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    public long countTotalItems(CustomerSearchRequest customerSearchRequest);
    CustomerDTO getOneCustomerEdit(Long id);
    CustomerDTO AddOrUpdateCustomer(CustomerDTO customerDTO);
    void DeleteCustomer(Long[] ids);
    ResponseDTO listStaffs(Long id);
    AssignmentCustomerDTO UpdateAssignmentCustomerService(AssignmentCustomerDTO assignmentCustomerDTO);

}
