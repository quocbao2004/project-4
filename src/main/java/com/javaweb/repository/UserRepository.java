package com.javaweb.repository;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> , UserRepositoryCustom {
    UserEntity findOneByUserNameAndStatus(String name, int status);
    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status,
                                                                                                  Pageable pageable);
    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);
    Page<UserEntity> findByStatusNot(int status, Pageable pageable);
    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);
    long countByStatusNot(int status);
    UserEntity findOneByUserName(String userName);
    UserEntity findOneByPhone(String phone);
    UserEntity findOneByEmail(String email);
    List<UserEntity> findByIdIn(List<Long> id);
    List<UserEntity> getAllUsers(Pageable pageable);
//    interface BuildingRepository extends JpaRepository<BuildingSearchResponse, Integer> BuildingRepositoryCustom {
//
//    }
}
