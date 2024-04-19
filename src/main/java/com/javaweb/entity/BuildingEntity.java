package com.javaweb.entity;


//import com.javaweb.repository.AssignmentRepository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="building")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "buildingId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    private List<RentAreaEntity>rentAreas = new ArrayList<>();

//    @OneToMany(mappedBy = "buildingId", fetch = FetchType.LAZY)
//    private List<AssignmentEntity>assignmentBuildings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "Assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "name")
    private String name;

    @Column(name ="numberofbasement")
    private Integer numberOfBasement;

    @Column(name ="ward")
    private String ward;

    @Column(name ="type")
    private String type;

    @Column(name ="district")
    private String district;

    @Column(name ="street")
    private String street;

    @Column(name ="floorarea")
    private Long floorarea;

    @Column(name ="direction")
    private String direction;

    @Column(name ="managername")
    private String managername;

    @Column(name ="managerphone")
    private String managerphone;

    @Column(name ="level")
    private Long level;

    @Column(name ="rentprice")
    private Long rentPrice;

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    @Column(name = "servicefee")
    private Long servicefee;

    @Column(name = "carfee")
    private Long carfee;

    @Column(name = "motofee")
    private  Long motofee;

    @Column(name ="overtimefee")
    private Long overtimefee;

    @Column(name ="waterfee")
    private Long waterfee;

    @Column(name = "electricityfee")
    private Long electricityfee;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "renttime")
    private String renttime;

    @Column(name = "decorationtime")
    private String decorationtime;

    @Column(name = "brokeragefee")
    private Long brokeragefee;

    @Column(name = "structure")
    private String structure;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    //    public List<AssignmentEntity> getAssignmentBuildings() {
//        return assignmentBuildings;
//    }
//    public void setAssignmentBuildings(List<AssignmentEntity> assignmentBuildings) {
//        this.assignmentBuildings = assignmentBuildings;
//    }
    public List<RentAreaEntity> getRentAreas() {
        return rentAreas;
    }
    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }
    public String getRentpricedescription() {
        return rentpricedescription;
    }
    public void setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
    }
    public Long getServicefee() {
        return servicefee;
    }
    public void setServicefee(Long servicefee) {
        this.servicefee = servicefee;
    }
    public Long getCarfee() {
        return carfee;
    }
    public void setCarfee(Long carfee) {
        this.carfee = carfee;
    }
    public Long getMotofee() {
        return motofee;
    }
    public void setMotofee(Long motofee) {
        this.motofee = motofee;
    }
    public Long getOvertimefee() {
        return overtimefee;
    }
    public void setOvertimefee(Long overtimefee) {
        this.overtimefee = overtimefee;
    }
    public Long getWaterfee() {
        return waterfee;
    }
    public void setWaterfee(Long waterfee) {
        this.waterfee = waterfee;
    }
    public Long getElectricityfee() {
        return electricityfee;
    }
    public void setElectricityfee(Long electricityfee) {
        this.electricityfee = electricityfee;
    }
    public String getDeposit() {
        return deposit;
    }
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public String getRenttime() {
        return renttime;
    }
    public void setRenttime(String renttime) {
        this.renttime = renttime;
    }
    public String getDecorationtime() {
        return decorationtime;
    }
    public void setDecorationtime(String decorationtime) {
        this.decorationtime = decorationtime;
    }
    public Long getBrokeragefee() {
        return brokeragefee;
    }
    public void setBrokeragefee(Long brokeragefee) {
        this.brokeragefee = brokeragefee;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getManagerphone() {
        return managerphone;
    }
    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }
    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public Long getFloorarea() {
        return floorarea;
    }
    public void setFloorarea(Long floorarea) {
        this.floorarea = floorarea;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getManagername() {
        return managername;
    }
    public void setManagername(String managername) {
        this.managername = managername;
    }
    public String getManagerphonenumber() {
        return managerphone;
    }
    public void setManagerphonenumber(String managerphonenumber) {
        this.managerphone = managerphonenumber;
    }
    public Long getLevel() {
        return level;
    }
    public void setLevel(Long level) {
        this.level = level;
    }
    public Long getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }
}