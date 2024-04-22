package com.javaweb.service.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.converter.ConverterSolve;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
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
        List<CustomerSearchResponse> res = new ArrayList<>();
        List<CustomerEntity>temp = customerRepository.findAll(customerSearchRequest, pageable);
        res = converterSolve.converterCustomer(temp);
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
    public  void AddOrUpdateCustomer(CustomerDTO customerDTO){
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerRepository.save(customerEntity);
    }

    @Override
    public void DeleteCustomer(Long[] ids){
        customerRepository.deleteByIdIn(ids);
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
    public void UpdateAssignmentCustomerService(AssignmentCustomerDTO assignmentCustomerDTO){
        CustomerEntity optionalCustomer = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        if (optionalCustomer != null) {
            List<UserEntity> staffEntities = userRepository.findAllById(assignmentCustomerDTO.getStaffs());
            optionalCustomer.setUserEntities(staffEntities);
            customerRepository.save(optionalCustomer);
        }
    }
}
