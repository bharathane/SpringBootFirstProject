package com.example.users.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Table(name="manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="managerid")
    private UUID managerId;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "manager")
    @JsonIgnoreProperties("manager")
    List<Users> users=new ArrayList<>();
    public Manager(){}
    public Manager(UUID managerId,String name){
        this.managerId=managerId;
        this.name=name;
    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
