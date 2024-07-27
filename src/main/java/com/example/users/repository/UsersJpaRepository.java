package com.example.users.repository;

import com.example.users.model.Manager;
import com.example.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UsersJpaRepository extends JpaRepository<Users,UUID> {
    List<Users> findByMobileNumber(String mobileNumber);
    List<Users> findByPanNum(String panNum);

}
