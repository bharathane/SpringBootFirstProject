package com.example.users.controller;

import com.example.users.model.Users;

import java.util.*;

public class RequestArrayObject {
    private List<UUID> userIds;
    private Users userObj;

    public RequestArrayObject(List<UUID> userIds, Users userObj) {
        this.userIds = userIds;
        this.userObj = userObj;
    }

    public Users getUserObj() {
        return userObj;
    }

    public List<UUID> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<UUID> userIds) {
        this.userIds = userIds;
    }

    public void setUserObj(Users userObj) {
        this.userObj = userObj;
    }
}

