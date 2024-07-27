package com.example.users.service;

import com.example.users.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.users.model.*;

@Service
public class ManagerService implements ManagerRepository {
    @Autowired
    private ManagerJpaRepository managerRepo;

  @Override
  public List<Manager> getListOfManagers(){
        return managerRepo.findAll();

  }

  @Override
    public Manager addNewManager(Manager obj){
      return  managerRepo.save(obj);
  }

}
