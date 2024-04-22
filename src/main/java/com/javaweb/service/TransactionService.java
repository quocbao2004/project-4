package com.javaweb.service;

import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    void AddOrUpdateTransaction(TransactionDTO transaction);
    List<TransactionDTO> getTransactionEditCSKH(Long id);
    public List<TransactionDTO> getTransactionEditDDX(Long id);
    public TransactionDTO findOneTransaction(Long id);
}
