package com.parking.maintenance.controller;

import com.parking.maintenance.entity.Center;
import com.parking.maintenance.service.ParkingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingCenterResource {

    @Autowired
    private ParkingCenterService parkingCenterService;

    //fetch applicable countries
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/applicableCountries")
    public ResponseEntity<List<String>> getApplicableCountries(){
        return parkingCenterService.getApplicableCountries();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/viewAllCenters/{country}")
    public ResponseEntity<List<Center>> getAllCenters(@PathVariable String country){
        return parkingCenterService.getAllCenters(country);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/viewCenterDetails/{centerId}")
    public ResponseEntity<Center> getCenterDetails(@PathVariable String centerId){
        return parkingCenterService.getCenterDetails(centerId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/modifyCenter")
    public ResponseEntity<String> modifyCenter(@RequestBody Center center){
        return parkingCenterService.modifyCenter(center);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteCenter")
    public ResponseEntity<String> deleteCenter(@RequestBody String centerId){
        return parkingCenterService.deleteCenter(centerId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createCenter")
    public ResponseEntity<String> createCenter(@RequestBody Center center){
        return parkingCenterService.createCenter(center);
    }
}
