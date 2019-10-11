package com.parking.maintenance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="PARKING_CENTER")
public class Center {
    @Id
    @Column(name="CENTER_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String centerId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "TOTAL_SLOTS")
    private long totalSlots;
    @Column(name = "AVAILABLE_SLOTS")
    private long availableSlots;
    @Column(name = "ACTIVE")
    private String active;
}
