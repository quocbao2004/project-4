package com.javaweb.service.impl;

import com.javaweb.converter.ConverterSolve;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ConverterSolve converterSolve;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable){
        List<CustomerEntity>temp = customerRepository.findAll(customerSearchRequest, pageable);
        List<CustomerSearchResponse> res  = converterSolve.converterCustomer(temp);
        return res;
    }

    @Override
    public long countTotalItems(CustomerSearchRequest customerSearchRequest) {
        long res = customerRepository.countTotalItems(customerSearchRequest);
        return res;
    }

    @Override
    public CustomerDTO getOneCustomerEdit(Long id) {
        CustomerEntity entity = customerRepository.findById(id).get();
        CustomerDTO result = modelMapper.map(entity, CustomerDTO.class);
        return result;
    }



    @Override
    public CustomerDTO AddOrUpdateCustomer(CustomerDTO customerDTO){
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setActive(1);
        if(customerDTO.getId() == null){
            customerEntity.setStatus("CHUA_XU_LY");
        } else{
            CustomerEntity temp = customerRepository.findById(customerDTO.getId()).get();
            customerEntity.setCreatedBy(temp.getCreatedBy());
            customerEntity.setCreatedDate(temp.getCreatedDate());

        }
        customerRepository.save(customerEntity);
        return customerDTO;
    }

    @Override
    public void DeleteCustomer(Long[] ids){
        for(Long id : ids){
            CustomerEntity customer = customerRepository.findById(id).get();
            customer.setActive(0);
            customerRepository.save(customer);
        }
    }

    @Override
    public ResponseDTO listStaffs(Long id) {
        CustomerEntity customer  = customerRepository.findById(id).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity>staffAssignment = customer.getUserEntities();

        List<StaffResponseDTO>staffResponseDTOS = new ArrayList<>();
        ResponseDTO reponseDTO = new ResponseDTO();
        for(UserEntity it : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        reponseDTO.setData(staffResponseDTOS);
        reponseDTO.setMessage("success");
            return reponseDTO;
    }

    @Override
    public AssignmentCustomerDTO UpdateAssignmentCustomerService(AssignmentCustomerDTO assignmentCustomerDTO){
        CustomerEntity optionalCustomer = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        if (optionalCustomer != null) {
            List<UserEntity> staffEntities = userRepository.findAllById(assignmentCustomerDTO.getStaffs());
            optionalCustomer.setUserEntities(staffEntities);
            customerRepository.save(optionalCustomer);
        }
        return assignmentCustomerDTO;
    }
}
