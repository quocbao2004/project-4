package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConverterSolve {
    @Autowired
    private ModelMapper modelmapper;

    public List<BuildingSearchResponse> coverterTemp(List<BuildingEntity> buildingEntities) {
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse building = modelmapper.map(item, BuildingSearchResponse.class);
            building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + GetDistrictFinal(item.getDistrict()));
            List<RentAreaEntity> rentAreaEntities = item.getRentAreas();
            String areaResult = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
            building.setRentArea(areaResult);
            result.add(building);
        }
        return result;
    }

    public List<CustomerSearchResponse> converterCustomer(List<CustomerEntity> customerEntities) {
        List<CustomerSearchResponse> result = new ArrayList<>();
        if(customerEntities != null){
            for (CustomerEntity item : customerEntities) {
                if(item.getStatus() != null){
                    if(item.getStatus().equals("DA_XU_LY")){
                        item.setStatus("Đã xử lý");
                    } else if(item.getStatus().equals("DANG_XU_LY")){
                        item.setStatus("Đang xử lý");
                    }
                    else{
                        item.setStatus("Chưa xử lý");
                    }
                }
                CustomerSearchResponse customer = modelmapper.map(item, CustomerSearchResponse.class);
                result.add(customer);
            }
        }
        return result;
    }

    public List<BuildingSearchResponse> coverterTemp(Page<BuildingEntity> buildingEntities) {
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse building = modelmapper.map(item, BuildingSearchResponse.class);
            building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + GetDistrictFinal(item.getDistrict()));
            List<RentAreaEntity> rentAreaEntities = item.getRentAreas();
            String areaResult = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
            building.setRentArea(areaResult);
            result.add(building);
        }
        return result;
    }

    public BuildingEntity converterEntity( BuildingSearchRequest buildingSearchRequest){
        return modelmapper.map(buildingSearchRequest, BuildingEntity.class);
    }

    String GetDistrictFinal(String item){
        String district = "";
        if (item != null) {
            Map<String, String> mapDistrict = District.type();
            if (mapDistrict != null) {
                String value = mapDistrict.get(item);
                if (value != null) {
                    district = value;
                }
            }
        }
        return district;
    }

    public String listToString(List<String> list){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
            if (i < list.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

    public List<RentAreaEntity>rentAreaBuildingConverter(BuildingDTO buildingDTO, BuildingEntity building){
        String[] rentAreasArrayString = buildingDTO.getRentArea().split(",");
        List<Long> rentAreas = new ArrayList<>();
        for (String area : rentAreasArrayString) {
            rentAreas.add(Long.parseLong(area.trim()));
        }
        List<RentAreaEntity> rentAreaBuildingEntity = new ArrayList<>();
        for (Long rentArea : rentAreas) {
            RentAreaEntity rent = new RentAreaEntity();
            rent.setValue(rentArea);
            rent.setBuildingId(building);
            rentAreaBuildingEntity.add(rent);
        }
        return rentAreaBuildingEntity;
    }

    public List<String> convertStringToList(String type){
        String[] danhSach = type.split(",");
        List<String> typeList = new ArrayList<>();
        for(String item : danhSach){
            typeList.add(item);
        }
        return typeList;
    }

    public List<TransactionDTO>converterTransaction(List<TransactionEntity> entities){
        List<TransactionDTO> result = new ArrayList<>();
        if(entities != null){
            for(TransactionEntity it : entities){
                if(it.getCreatedDate().equals(it.getModifiedDate())){
                    it.setModifiedDate(null);
                }
                if(it.getCreatedBy().equals(it.getModifiedBy()) && it.getModifiedDate() == null){
                    it.setModifiedBy(null);
                }
                TransactionDTO dto = modelmapper.map(it, TransactionDTO.class);
                result.add(dto);
            }
        }
        return result;
    }
}
