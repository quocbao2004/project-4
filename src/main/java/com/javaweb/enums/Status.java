package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Status {
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý"),
    CHUA_XU_LY("Chưa xử lý");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public static Map<String, String> StatusType() {
        Map<String, String> typeCodes = new LinkedHashMap<>();
        for (Status type : Status.values()) {
            typeCodes.put(type.name(), type.getName());
        }
        return typeCodes;
    }

    public String getName() {
        return name;
    }
}
