package com.example.users.repository;

import com.example.users.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface ManagerJpaRepository extends JpaRepository<Manager,UUID> {

}
