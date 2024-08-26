package com.marzz.maintenance_management_service.controller;

import com.marzz.maintenance_management_service.dto.AuthRequestDto;
import com.marzz.maintenance_management_service.dto.AuthResponseDto;
import com.marzz.maintenance_management_service.model.User;
import com.marzz.maintenance_management_service.service.JwtUtilService;
import com.marzz.maintenance_management_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping("/api/maintenance/service/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> auth(@RequestBody AuthRequestDto authRequestDto) {

        try {
            //1 - Authenticate user
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequestDto.getUsername(), authRequestDto.getPassword()
            ));

            //2 Validate the user in the database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequestDto.getUsername());

            //3 Generate JWT token
            String jwt = this.jwtUtilService.generateToken(userDetails);

            //4 Generate response
            User user = this.userService.getUserByUsername(authRequestDto.getUsername());

            AuthResponseDto response = new AuthResponseDto(
                    user.getId(),
                    user.getUsername(),
                    jwt
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error Authentication:::" + e.getMessage());
        }
    }
}
