package com.javaweb.service;

import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO AddOrUpdateTransaction(TransactionDTO transaction);
    List<TransactionDTO> getTransactionEditCSKH(Long id);
    List<TransactionDTO> getTransactionEditDDX(Long id);
    TransactionDTO findOneTransaction(Long id);
}
