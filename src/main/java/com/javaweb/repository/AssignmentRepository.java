//package com.javaweb.repository;
//
//import com.javaweb.entity.AssignmentEntity;
//import com.javaweb.entity.BuildingEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
//    @Transactional
//    void deleteByBuildingId(BuildingEntity BuildingId);
//    List<AssignmentEntity>findByBuildingId(BuildingEntity building);
//}