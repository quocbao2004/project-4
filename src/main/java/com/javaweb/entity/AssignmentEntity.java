//package com.javaweb.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name= "assignmentbuilding")
//public class AssignmentEntity extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
////    @Column(name = "staffid")
////    private Long staffid;
//
//    @ManyToOne
//    @JoinColumn(name ="buildingid")
//    private BuildingEntity buildingId;
//
//    @ManyToOne
//    @JoinColumn(name = "staffid")
//    private UserEntity staffid;
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public UserEntity getStaffid() {
//        return staffid;
//    }
//
//    public void setStaffid(UserEntity staffid) {
//        this.staffid = staffid;
//    }
//
//    public BuildingEntity getBuildingId() {
//        return buildingId;
//    }
//
//    public void setBuildingId(BuildingEntity building) {
//        this.buildingId = building;
//    }
//}
