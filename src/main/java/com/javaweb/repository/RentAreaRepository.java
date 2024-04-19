package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository  extends JpaRepository<RentAreaEntity, Long> {
    void deleteByBuildingId(BuildingEntity building);
    void deleteAllByBuildingId(BuildingEntity building);
    List<RentAreaEntity>findByBuildingId(BuildingEntity building);
}
