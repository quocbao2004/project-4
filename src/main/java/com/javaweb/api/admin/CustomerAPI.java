package com.javaweb.api.admin;


import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import com.javaweb.service.impl.TransactionServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value="customerOfAdmin")
@RequestMapping("/api/customer")

public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CustomerDTO> AddOrUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.AddOrUpdateCustomer(customerDTO));
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

    @PostMapping("/assignment-customer")
    public ResponseEntity<AssignmentCustomerDTO> updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
       return ResponseEntity.ok( customerService.UpdateAssignmentCustomerService(assignmentCustomerDTO));
    }

    @PostMapping("/transaction-type")
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO transactionDTO){
        return ResponseEntity.ok(transactionService.AddOrUpdateTransaction(transactionDTO));
    }

    @GetMapping("/{id}/transaction-detail")
    public ResponseEntity<TransactionDTO> loadTransactionDetails(@PathVariable Long id) {
        Optional<TransactionEntity> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            TransactionEntity transaction = optionalTransaction.get();
            TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
            return ResponseEntity.ok(transactionDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
