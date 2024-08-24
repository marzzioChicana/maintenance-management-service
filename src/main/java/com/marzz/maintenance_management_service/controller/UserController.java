package com.marzz.maintenance_management_service.controller;

import com.marzz.maintenance_management_service.dto.UserDTO;
import com.marzz.maintenance_management_service.exception.ResourceNotFoundException;
import com.marzz.maintenance_management_service.exception.ValidationException;
import com.marzz.maintenance_management_service.model.User;
import com.marzz.maintenance_management_service.repository.UserRepository;
import com.marzz.maintenance_management_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api/maintenance/service/v1")
public class UserController {
    @Autowired
    private UserService userService;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        validateUser(userDTO);
        existsUserByName(userDTO.getName());
        existsUserByEmail(userDTO.getEmail());
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        boolean isExist = userRepository.existsById(id);
        if (isExist) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("User not found for this id :: " + id);
        }
    }

    private void validateUser(UserDTO userDTO) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(hotmail|gmail)\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(userDTO.getEmail()).matches()) {
            throw new ValidationException("Email must be hotmail.com or gmail.com");
        }else if(userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()){
            throw new ValidationException("Email must be mandatory");
        }

        if(userDTO.getName() == null || userDTO.getName().trim().isEmpty()){
            throw new ValidationException("Name must be mandatory");
        }

        if(userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()){
            throw new ValidationException("Password must be mandatory");
        }
    }

    private void existsUserByName(String name) {
        if (userService.getUserByName(name) != null) {
            throw new ValidationException("Username already exists");
        }
    }

    private void existsUserByEmail(String email) {
        if (userService.getUserByEmail(email) != null) {
            throw new ValidationException("Email already exists");
        }
    }
}
