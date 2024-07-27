package com.example.users.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Table(name="users")
@JsonPropertyOrder({ "userId", "fullName", "panNum", "mobileNumber", "createdAt", "updatedAt", "isActive", "manager" })
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    private UUID userId;
    @Column(name="fullname")
    private  String fullName;
    @Column(name="pannum",nullable = false, unique = true)
    private String panNum;
    @Column(name="mobilenumber",nullable = false,unique = true)
    private  String mobileNumber;
    @Column(name="createdat")
    private String createdAt;
    @Column(name="updatedat")
    private String updatedAt;
    @Column(name="isactive")
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name="managerid")
    @JsonIgnoreProperties("users")
    private Manager manager;

    public Users(){

    }

    public Users(UUID userId,String fullName,String panNum, String mobileNumber,String createdAt,String updatedAt,boolean isActive,Manager manager){
        this.userId=userId;
        this.fullName=fullName;
        this.panNum=panNum;
        this.mobileNumber=mobileNumber;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.isActive=isActive;
        this.manager=manager;

    }



    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    public String getPanNum() {
        return panNum;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
