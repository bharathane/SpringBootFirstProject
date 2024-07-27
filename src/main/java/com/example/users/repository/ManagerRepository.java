package com.example.users.repository;

import com.example.users.model.*;
import java.util.*;
public interface ManagerRepository {

    List<Manager> getListOfManagers();

    Manager addNewManager(Manager obj);
}
