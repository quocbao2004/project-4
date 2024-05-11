package com.javaweb.service.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.ConverterSolve;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
//import com.javaweb.repository.AssignmentRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ConverterSolve convertersolve;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @PersistenceContext
    private EntityManager entityManager;

    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable){
        // TODO Auto-generated method stub
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder, pageable);
        for(BuildingEntity buildingEntity : buildingEntities){
            List<RentAreaEntity>rentAreas = rentAreaRepository.findByBuildingId(buildingEntity);
            buildingEntity.setRentAreas(rentAreas);
        }
        List<BuildingSearchResponse>result = convertersolve.coverterTemp(buildingEntities);
        return result;
    }

    @Override
    public long countTotalItems(BuildingSearchRequest buildingSearchRequest){
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        long res = buildingRepository.countTotalItems(buildingSearchBuilder);
        return res;
    }

    @Override
    @Transactional
    public void AddOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity building = modelMapper.map(buildingDTO, BuildingEntity.class);
        String typeCode = convertersolve.listToString(buildingDTO.getType());
        building.setType(typeCode);
        if(buildingDTO.getImageBase64() != null){
            saveThumbnail(buildingDTO, building);
            if(buildingDTO.getId() != null){
                BuildingEntity buildingTemp = buildingRepository.findById(buildingDTO.getId()).get();
                building.setUserEntities(buildingTemp.getUserEntities());
            }
        }
        else{
            if(buildingDTO.getId() != null){
                BuildingEntity buildingTemp = buildingRepository.findById(buildingDTO.getId()).get();
                building.setAvatar(buildingTemp.getAvatar());
                building.setUserEntities(buildingTemp.getUserEntities());
            }
        }
        if(buildingDTO.getRentArea() != ""){
            List<RentAreaEntity> rentAreaBuildingEntity = convertersolve.rentAreaBuildingConverter(buildingDTO, building);
            building.setRentAreas(rentAreaBuildingEntity);
        }
        buildingRepository.save(building);

    }

    @Override
    public void DeleteBuilding(Long[] ids) {
        for (Long it : ids) {
            BuildingEntity buildingEntity = buildingRepository.findById(Long.valueOf(it)).get();
            rentAreaRepository.deleteByBuildingId(buildingEntity);
        }
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building  = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity>staffAssignment = building.getUserEntities();

        List<StaffResponseDTO>staffResponseDTOS = new ArrayList<>();
        ResponseDTO reponseDTO = new ResponseDTO();
        for(UserEntity it : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        reponseDTO.setData(staffResponseDTOS);
        reponseDTO.setMessage("success");
        return reponseDTO;
    }

    @Override
    public void UpdateAssignmentBuildingService(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity optionalBuilding = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        if (optionalBuilding != null) {
            List<UserEntity> staffEntities = userRepository.findAllById(assignmentBuildingDTO.getStaffs());
            optionalBuilding.setUserEntities(staffEntities);
            buildingRepository.save(optionalBuilding);
        }

    }

    public BuildingDTO getOneBuildingEdit(Long id){
        BuildingEntity building = buildingRepository.findById(id).get();
        BuildingDTO buildingFind = modelMapper.map(building, BuildingDTO.class);
        List<String> typeList = convertersolve.convertStringToList(building.getType());
        buildingFind.setType(typeList);

        List<RentAreaEntity>rentAreas = rentAreaRepository.findByBuildingId(building);
        StringBuilder rentAreaStringBuilder = new StringBuilder();
        int cnt = 0;
        for(RentAreaEntity it : rentAreas){
            rentAreaStringBuilder.append(it.getValue());
            if(cnt < rentAreas.size() - 1){
                rentAreaStringBuilder.append(", ");
                cnt++;
            }
        }
        buildingFind.setRentArea(rentAreaStringBuilder.toString());
        buildingFind.setImage(building.getAvatar());
        buildingFind.setImageName(building.getAvatar());
        buildingFind.setImageBase64(building.getAvatar());
        return buildingFind;
    }

    public void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
                    File file = new File("C://home/office" + buildingEntity.getAvatar());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }
}