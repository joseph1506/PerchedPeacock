package com.parking.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "VEHICLE_MASTER")
public class Vehicle {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "REGISTRATION_ID")
    private String registrationId;
    @Column(name = "MAKE")
    private String makeNModel;
    @Column(name = "WEIGHT")
    private long weight;
    @Column(name = "ACTIVE")
    private String active;
}
