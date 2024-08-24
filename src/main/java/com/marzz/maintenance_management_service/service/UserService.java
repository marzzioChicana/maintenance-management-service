package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.dto.UserDTO;
import com.marzz.maintenance_management_service.model.User;

import java.util.List;

public interface UserService {
    public abstract User createUser(UserDTO userDTO);
    public abstract void updateUser(UserDTO userDTO);
    public abstract void deleteUser(int id);
    public abstract User getUserById(int id);
    public abstract User getUserByName(String name);
    public abstract User getUserByEmail(String email);
    public abstract List<User> getUsers();
}
