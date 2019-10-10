package com.parking.user.controllers;

import com.parking.user.constants.AppConstants;
import com.parking.user.constants.Role;
import com.parking.user.entity.User;
import com.parking.user.exception.DisabledUserException;
import com.parking.user.exception.InvalidUserCredentialsException;
import com.parking.user.model.JwtRequest;
import com.parking.user.model.JwtResponse;
import com.parking.user.service.TokenService;
import com.parking.user.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody JwtRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledUserException("User Inactive");
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }
        UserDetails userDetails = userAuthService.loadUserByUsername(request.getUserName());
        String roles = userDetails.getAuthorities().stream()
                .map(r -> r.getAuthority())
                .collect(Collectors.joining(AppConstants.COMMA_DELIMITER.getValue()));
        User user = new User();
        user.setUserId(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRoles(roles);
        String token = tokenService.generateToken(user);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody User user) {
        return userAuthService.createUser(user, Arrays.asList(Role.USER));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user){
        return userAuthService.createUser(user);
    }

    // change password  -- for admin and user
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody User user){
        return userAuthService.changePassword(user);
    }

    // modify user details  -- for admin and user
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/editProfile")
    public ResponseEntity<String> editProfile(@RequestBody User user){
        return userAuthService.editProfile(user);
    }


    // view users  -- for admin
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return userAuthService.getAllUsers();
    }

    //delete user --for admin
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        return userAuthService.deleteUser(userId);
    }

    //


}
