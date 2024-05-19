package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping("/api/building")

public class BuildingAPI {
    @Autowired
    BuildingService buildingService;
    @Autowired
    private BuildingService buildingservice;

    @PostMapping
    public void AddOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingservice.AddOrUpdateBuilding(buildingDTO);
    }

    @DeleteMapping(value="/{ids}")
    @Transactional
    public void deleteBuilding(@PathVariable Long[] ids) {
        buildingservice.DeleteBuilding(ids);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }

    @PostMapping("/building-assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.UpdateAssignmentBuildingService(assignmentBuildingDTO);
    }

    @GetMapping
    public List<BuildingSearchResponse>getBuilding(@ModelAttribute BuildingSearchRequest buildingSearchRequest, Pageable pageable){
        List<BuildingSearchResponse>res = buildingService.findAll(buildingSearchRequest,  pageable);
        return res;
    }
}
