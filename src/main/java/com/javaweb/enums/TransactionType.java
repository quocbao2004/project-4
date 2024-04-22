package com.javaweb.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public static Map<String, String> transactionType() {
        Map<String, String> typeCodes = new LinkedHashMap<>();
        for (TransactionType type : TransactionType.values()) {
            typeCodes.put(type.name(), type.getName());
        }
        return typeCodes;
    }

    public String getName() {
        return name;
    }
}
