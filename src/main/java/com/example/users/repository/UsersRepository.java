package com.example.users.repository;

import com.example.users.model.Users;

import java.util.*;

public interface UsersRepository {
    List<Users> getAllUsers();

    Users addNewUser(Users obj);

    List<Users> getSpecificUser(Users obj);

    String deleteUser(Users obj);
    String updateUsers(List<UUID> userIds,Users userObj);
}
