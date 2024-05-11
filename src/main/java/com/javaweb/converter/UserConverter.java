package com.javaweb.converter;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }

    public UserEntity convertToEntity2(UserDTO dto, UserEntity entity) {
        if (dto == null || entity == null) {
            return entity;
        }

        Class<?> dtoClass = dto.getClass();
        Class<?> entityClass = entity.getClass();

        Field[] dtoFields = dtoClass.getDeclaredFields();

        for (Field field : dtoFields) {
            try {
                // Cho phép truy cập vào trường private
                field.setAccessible(true);

                // Lấy giá trị của trường từ DTO
                Object value = field.get(dto);

                // Kiểm tra xem giá trị của trường có null không
                if (value != null) {
                    // Lấy tên trường
                    String fieldName = field.getName();

                    // Tìm trường tương ứng trong entity
                    Field entityField = entityClass.getDeclaredField(fieldName);

                    // Cho phép truy cập vào trường private trong entity
                    entityField.setAccessible(true);

                    // Đặt giá trị của trường trong entity bằng giá trị từ DTO
                    entityField.set(entity, value);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return entity;
    }


}
