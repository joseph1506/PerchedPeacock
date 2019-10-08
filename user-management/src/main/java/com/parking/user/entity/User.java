package com.parking.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
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
}
