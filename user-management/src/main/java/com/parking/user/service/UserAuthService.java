package com.parking.user.service;

import com.parking.user.constants.AppConstants;
import com.parking.user.constants.Role;
import com.parking.user.entity.User;
import com.parking.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = this.findActualUserById(userId);
        Set<String> roles = user.getRolesAsSet();
        List<GrantedAuthority> grantedAuthorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(),
                grantedAuthorities);
    }

    private void checkAndThrowIFUserNotFound(Optional<User> userOptional, String userId) throws UsernameNotFoundException {
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("User '" + userId + "' not found.");
        }
    }

    public void saveUser(User user, boolean encode) {
        if(encode){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setActive("Y");
        if(StringUtils.isEmpty(user.getRoles())){
            user.setRoles(Role.USER.name());
        }
        userRepository.save(user);
    }

    public ResponseEntity<String> createUser(User user, List<Role> roles){
        if(roles!=null){
            user.setRoles(roles.stream()
                    .map(role-> role.name())
                    .collect(Collectors.joining(AppConstants.COMMA_DELIMITER.getValue())));
        }
        this.saveUser(user,true);
        return getMessageResponseEntity(AppConstants.USER_CREATION_SUCCESS.getValue(), HttpStatus.OK);
    }

    public ResponseEntity<String> createUser(User user){
        return this.createUser(user,null);
    }

    public ResponseEntity<String> changePassword(User user) throws UsernameNotFoundException {
        User actualUserDetails = this.findActualUserById(user.getUserId());
        actualUserDetails.setPassword(user.getPassword());
        this.saveUser(actualUserDetails,true);
        return getMessageResponseEntity(AppConstants.USER_PASSWORD_CHANGED.getValue(), HttpStatus.OK);
    }

    public ResponseEntity<String> editProfile(User user) {
        User actualUserDetails = this.findActualUserById(user.getUserId());
        user.setPassword(actualUserDetails.getPassword());
        this.saveUser(user,false);
        return getMessageResponseEntity(AppConstants.USER_MODIFICATION_SUCCESS.getValue(), HttpStatus.OK);
    }

    public User findActualUserById(String userId){
        Optional<User> actualUserOptional = userRepository.findById(userId);
        checkAndThrowIFUserNotFound(actualUserOptional,userId);
        return actualUserOptional.get();
    }


    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users= (List<User>) userRepository.findAll();
        users.stream()
                .forEach(user-> user.setPassword(AppConstants.MASK.getValue()));
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(String userId) {
        boolean userExists = userRepository.existsById(userId);
        if(userExists){
            userRepository.deleteById(userId);
        }
        return getMessageResponseEntity(AppConstants.USER_DELETION_SUCCESS.getValue(), HttpStatus.OK);
    }

    private ResponseEntity<String> getMessageResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<String>(message, status);
    }
}
