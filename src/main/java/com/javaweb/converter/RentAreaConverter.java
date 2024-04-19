package com.javaweb.converter;

import com.javaweb.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RentAreaConverter {
    public static List<Long> convertToLongList(List<RentAreaEntity> rentAreaEntities) {
        List<Long> rentAreas = new ArrayList<>();
        for (RentAreaEntity rentAreaEntity : rentAreaEntities) {
            rentAreas.add(rentAreaEntity.getValue());
        }
        return rentAreas;
    }
}
