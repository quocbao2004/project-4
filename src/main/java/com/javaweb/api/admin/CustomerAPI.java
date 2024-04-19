package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController(value="customerOfAdmin")
@RequestMapping("/api/customer")

public class CustomerAPI {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public void AddOrUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.AddOrUpdateCustomer(customerDTO);
    }

    @DeleteMapping(value="/{ids}")
    @Transactional
    public void DeleteCustomer(@PathVariable Long[] ids){
        customerService.DeleteCustomer(ids);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = customerService.listStaffs(id);
        return result;
    }

    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        customerService.UpdateAssignmentCustomerService(assignmentCustomerDTO);
    }
}
