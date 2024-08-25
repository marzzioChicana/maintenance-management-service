package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.dto.UserDto;
import com.marzz.maintenance_management_service.model.User;

import java.util.List;

public interface UserService {
    public abstract User createUser(UserDto userDTO);
    public abstract void updateUser(UserDto userDTO);
    public abstract void deleteUser(int id);
    public abstract User getUserById(int id);
    public abstract User getUserByUsername(String username);
    public abstract User getUserByEmail(String email);
    public abstract List<User> getUsers();
}
