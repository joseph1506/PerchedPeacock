package com.parking.user.service;

import com.parking.user.constants.AppConstants;
import com.parking.user.entity.User;
import com.parking.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<String> roles = user.getRolesAsSet();
            List<GrantedAuthority> grantedAuthorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(),
                    grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User '" + userId + "' not found.");
        }
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive("Y");
        if(StringUtils.isEmpty(user.getRoles())){
            user.setRoles(AppConstants.USER.getValue());
        }
        userRepository.save(user);
    }
}
