package com.parking.maintenance.controller;

import com.parking.maintenance.entity.Slot;
import com.parking.maintenance.model.AddSlotRequest;
import com.parking.maintenance.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SlotResource {

    @Autowired
    private SlotService slotService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addSlots")
    public ResponseEntity<String> addSlotsToCenter(@RequestBody AddSlotRequest addSlot){
        return slotService.addSlotsToCenter(addSlot.getCenterId(),addSlot.getNoOfSlots());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteSlot")
    public ResponseEntity<String> deleteSlot(@RequestBody String slotId){
        return slotService.deleteSlot(slotId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/viewAllParkingSlots/{centerId}")
    public ResponseEntity<List<Slot>> getAllParkingSlots(@PathVariable String centerId){
        return slotService.getAllParkingSlots(centerId);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/viewAllAvailableParkingSlots/{centerId}")
    public ResponseEntity<List<Slot>> getAllAvailableParkingSlots(@PathVariable String centerId){
        return slotService.getAllAvailableParkingSlots(centerId);
    }

}
