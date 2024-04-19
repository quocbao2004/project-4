package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    public void AddOrUpdateBuilding(BuildingDTO buildingDTO);
    void DeleteBuilding(Long[] ids);
    ResponseDTO listStaffs(Long buildingId);
    void UpdateAssignmentBuildingService(AssignmentBuildingDTO assignmentBuildingDTO);
    BuildingDTO getOneBuildingEdit(Long id);
    long countTotalItems(BuildingSearchRequest buildingSearchRequest);
}
