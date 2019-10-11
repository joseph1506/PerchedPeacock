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
@Table(name = "SLOT_STATUS")
public class Slot {
    @Id
    @Column(name = "SLOT_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String slotId;
    @Column(name = "CENTER_ID")
    private String centerId;
    @Column(name = "SLOT_NO")
    private long slotNo;
    @Column(name = "SLOT_NAME")
    private String slotName;
    @Column(name = "STATUS")
    private String status;
}
