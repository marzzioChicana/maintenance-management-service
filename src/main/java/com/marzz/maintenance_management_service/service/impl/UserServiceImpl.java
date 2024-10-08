package com.marzz.maintenance_management_service.service.impl;

import com.marzz.maintenance_management_service.dto.UserDto;
import com.marzz.maintenance_management_service.exception.ResourceNotFoundException;
import com.marzz.maintenance_management_service.model.User;
import com.marzz.maintenance_management_service.repository.UserRepository;
import com.marzz.maintenance_management_service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User createUser(UserDto userDTO) {
        User user = DtoToEntity(userDTO);
        return userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDTO) {
        User user = DtoToEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private UserDto EntityToDto(User user) { return modelMapper.map(user, UserDto.class); }

    private User DtoToEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
