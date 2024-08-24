package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String username);
    User findByEmail(String email);
}
