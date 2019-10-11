package com.parking.maintenance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddSlotRequest {
    private String centerId;
    private long noOfSlots;
}
