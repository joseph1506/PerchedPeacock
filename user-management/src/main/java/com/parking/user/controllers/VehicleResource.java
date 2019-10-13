package com.parking.user.controllers;

import com.parking.user.entity.User;
import com.parking.user.entity.Vehicle;
import com.parking.user.exception.VehicleAlreadyExistsException;
import com.parking.user.exception.VehicleNotFoundException;
import com.parking.user.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleResource {

    @Autowired
    private VehicleService vehicleService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/getActiveVehicles")
    public ResponseEntity<List<Vehicle>> getActiveVehicles(){
        return vehicleService.getActiveVehicles();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/viewVehicleDetails/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleDetails(@PathVariable String vehicleId) throws Exception {
        return vehicleService.getVehicleDetails(vehicleId);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/addVehicle")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) throws Exception {
        return vehicleService.addVehicle(vehicle);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/modifyVehicle")
    public ResponseEntity<String> modifyVehicle(@RequestBody Vehicle vehicle) throws Exception {
        return vehicleService.modifyVehicle(vehicle);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/deleteVehicle/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleId) throws Exception {
        return vehicleService.deleteVehicle(vehicleId);
    }

}
