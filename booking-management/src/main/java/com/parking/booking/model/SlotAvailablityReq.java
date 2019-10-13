package com.parking.booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SlotAvailablityReq{
    private List<String> slotIds;
    private Date fromTime;
    private Date toTime;
}
