package com.javaweb.repository.custom;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchBuilder BuildingSearchBuilder, Pageable pageable );
    public long countTotalItems(BuildingSearchBuilder buildingSearchBuilder);
}
