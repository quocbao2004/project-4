package com.javaweb.service.impl;

import com.javaweb.converter.ConverterSolve;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConverterSolve converterSolve;
    @Override
    public TransactionDTO AddOrUpdateTransaction(TransactionDTO transaction){
        TransactionEntity transactionEntity = modelMapper.map(transaction, TransactionEntity.class);
        CustomerEntity customer  = customerRepository.findById(transaction.getCustomerId()).get();
        transactionEntity.setCustomer(customer);
        if(transaction.getId() != null){
            TransactionEntity temp = transactionRepository.findById(transaction.getId()).get();
            transactionEntity.setCreatedBy(temp.getCreatedBy());
            transactionEntity.setCreatedDate(temp.getCreatedDate());
        }
        transactionRepository.save(transactionEntity);
        return transaction;
    }

    @Override
    public TransactionDTO findOneTransaction(Long id){
        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> getTransactionEditCSKH(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        List<TransactionEntity>entity = transactionRepository.findAllByCustomerAndCode(customerEntity, "CSKH");
        List<TransactionDTO> result = converterSolve.converterTransaction(entity);
        return result;
    }

    @Override
    public List<TransactionDTO> getTransactionEditDDX(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        List<TransactionEntity>entity = transactionRepository.findAllByCustomerAndCode(customerEntity, "DDX");
        List<TransactionDTO> result = converterSolve.converterTransaction(entity);
        return result;
    }
}
