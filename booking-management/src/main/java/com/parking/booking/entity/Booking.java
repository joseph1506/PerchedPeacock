package com.parking.booking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOKING_MASTER")
public class Booking {
    @Id
    @Column(name="ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name="USER_ID")
    private String userId;
    @Column(name="VEHICLE_ID")
    private String vehicleId;
    @Column(name="SLOT_ID")
    private String slotId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FROM_TIME")
    private Date fromTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TO_TIME")
    private Date toTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED")
    private Date created;
    @Column(name="STATUS")
    private String status;
}
