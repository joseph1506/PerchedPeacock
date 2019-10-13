package com.parking.booking.service;

import com.parking.booking.entity.User;
import com.parking.booking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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


    public User findActualUserById(String userId){
        Optional<User> actualUserOptional = userRepository.findById(userId);
        checkAndThrowIFUserNotFound(actualUserOptional,userId);
        return actualUserOptional.get();
    }

}
