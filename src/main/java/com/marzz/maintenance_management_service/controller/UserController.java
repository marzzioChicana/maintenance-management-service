package com.marzz.maintenance_management_service.controller;

import com.marzz.maintenance_management_service.dto.UserDto;
import com.marzz.maintenance_management_service.exception.ResourceNotFoundException;
import com.marzz.maintenance_management_service.exception.ValidationException;
import com.marzz.maintenance_management_service.model.User;
import com.marzz.maintenance_management_service.repository.UserRepository;
import com.marzz.maintenance_management_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping("/api/maintenance/service/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDTO) {
        validateUser(userDTO);
        existsUserByUsername(userDTO.getUsername());
        existsUserByEmail(userDTO.getEmail());
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        boolean isExist = userRepository.existsById(id);
        if (isExist) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("User not found for this id :: " + id);
        }
    }

    private void validateUser(UserDto userDTO) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(hotmail|gmail)\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(userDTO.getEmail()).matches()) {
            throw new ValidationException("Email must be hotmail.com or gmail.com");
        }else if(userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()){
            throw new ValidationException("Email must be mandatory");
        }

        if(userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()){
            throw new ValidationException("Username must be mandatory");
        }

        if(userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()){
            throw new ValidationException("Password must be mandatory");
        }
    }

    private void existsUserByUsername(String username) {
        if (userService.getUserByUsername(username) != null) {
            throw new ValidationException("Username already exists");
        }
    }

    private void existsUserByEmail(String email) {
        if (userService.getUserByEmail(email) != null) {
            throw new ValidationException("Email already exists");
        }
    }
}
