package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TypeCode {
    TANG_TRET("Tầng Trệt"),
    NOI_THAT("Nội Thất"),
    NGUYEN_CAN("Nguyên Căn");

    private final String typeCode;
    TypeCode(String typeCode){
        this.typeCode = typeCode;
    }

    public static Map<String, String> type(){
        Map<String, String>typeCodes = new TreeMap<>();
        for(TypeCode item : TypeCode.values()){
            typeCodes.put(item.toString(), item.typeCode);
        }
        return typeCodes;
    }
}
