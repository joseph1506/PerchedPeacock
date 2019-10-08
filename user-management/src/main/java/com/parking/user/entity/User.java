package com.parking.user.entity;

import com.parking.user.constants.AppConstants;
import com.parking.user.constants.TokenConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_MASTER")
public class User {
    @Id
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String mobile;
    @Column(name = "ROLES")
    private String roles;
    @Column(name = "COUNTRY")
    private String countryCode;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ACTIVE")
    private String active;

    public Set<String> getRolesAsSet(){
        return new HashSet<String>(Arrays.asList(this.getRoles().split(AppConstants.COMMA_DELIMITER.getValue())));
    }
}
