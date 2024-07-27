package com.example.users.controller;

import com.example.users.model.*;
import com.example.users.service.UsersJpaService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UsersController {
    @Autowired
    public UsersJpaService userServ;

    @GetMapping("/users")
    public List<Users> allUsers(){
        return userServ.getAllUsers();
    }
    @PostMapping("/users")
    public Users postUser(@RequestBody Users userObj){
        return  userServ.addNewUser(userObj);
    }

    @PostMapping("/specificUser")
    public List<Users> getSpecific(@RequestBody(required = false) Users obj){
        if(obj==null){
            return allUsers();
        }
        return  userServ.getSpecificUser(obj);
    }

    @PostMapping("/deleteUser")
    public String deleteUserOf(@RequestBody Users obj){
        return userServ.deleteUser(obj);
    }

    @PostMapping("/bulkUpdate")
    public String updateBulk(@RequestBody RequestArrayObject obj){
        return  userServ.updateUsers(obj.getUserIds(),obj.getUserObj());
    }
}
