package com.example.users.controller;

import com.example.users.model.Manager;
import com.example.users.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class Managercontroller {
    @Autowired
    public ManagerService managerServ;

    @GetMapping("/managers")
    public List<Manager> getAllManagers(){
        return  managerServ.getListOfManagers();
    }

    @PostMapping("/postManager")
    public Manager postNew(@RequestBody Manager obj){
        return  managerServ.addNewManager(obj);
    }
}
