package com.parking.user.service;

import com.parking.user.constants.AppConstants;
import com.parking.user.entity.Vehicle;
import com.parking.user.exception.VehicleAlreadyExistsException;
import com.parking.user.exception.VehicleNotFoundException;
import com.parking.user.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResponseEntity<List<Vehicle>> getActiveVehicles() {
        return new ResponseEntity<List<Vehicle>>(vehicleRepository.findVehiclesByActive(AppConstants.ACTIVE.getValue()), HttpStatus.OK);
    }

    public ResponseEntity<Vehicle> getVehicleDetails(String vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = findVehicle(vehicleId);
        return new ResponseEntity<>(vehicle,HttpStatus.OK);
    }

    private Vehicle findVehicle(String vehicleId) throws VehicleNotFoundException {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if(vehicleOptional.isPresent()){
            return vehicleOptional.get();
        } else {
            throw new VehicleNotFoundException(AppConstants.VEHICLE_NOT_FOUND.getValue());
        }
    }

    public ResponseEntity<String> addVehicle(Vehicle vehicle) throws VehicleAlreadyExistsException {
        Vehicle alreadyExisting = vehicleRepository.findVehicleByRegistrationIdAndUserId(vehicle.getRegistrationId(),vehicle.getUserId());
        saveIfNotExisting(vehicle, alreadyExisting);
        return getMessageResponseEntity(AppConstants.VEHICLE_ADDED_SUCCESS.getValue(),HttpStatus.OK);
    }

    private void saveIfNotExisting(Vehicle vehicle, Vehicle alreadyExisting) throws VehicleAlreadyExistsException {
        if(alreadyExisting!=null){
            vehicle.setActive(AppConstants.ACTIVE.getValue());
            vehicleRepository.save(vehicle);
        } else {
            throw new VehicleAlreadyExistsException(AppConstants.VEHICLE_EXISTS.getValue());
        }
    }

    public ResponseEntity<String> modifyVehicle(Vehicle vehicle) throws VehicleNotFoundException, VehicleAlreadyExistsException {
        Vehicle vehicleFromDB = findVehicle(vehicle.getId());
        Vehicle alreadyExistingRegistration = vehicleRepository.findVehicleByRegistrationIdAndUserIdAndIdNot(vehicle.getRegistrationId(),vehicle.getUserId(),vehicle.getId());
        saveIfNotExisting(vehicle,alreadyExistingRegistration);
        return getMessageResponseEntity(AppConstants.VEHICLE_MODIFIED_SUCCESS.getValue(),HttpStatus.OK);
    }

    private ResponseEntity<String> getMessageResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<String>(message, status);
    }

    public ResponseEntity<String> deleteVehicle(String vehicleId) throws VehicleNotFoundException {
        findVehicle(vehicleId);
        vehicleRepository.deleteById(vehicleId);
        return getMessageResponseEntity(AppConstants.VEHICLE_DELETED_SUCCESS.getValue(), HttpStatus.OK);
    }
}
