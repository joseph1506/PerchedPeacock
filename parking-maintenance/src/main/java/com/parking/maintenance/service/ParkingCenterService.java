package com.parking.maintenance.service;

import com.parking.maintenance.constants.AppConstants;
import com.parking.maintenance.entity.Center;
import com.parking.maintenance.repositories.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ParkingCenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private SlotService slotService;

    public ResponseEntity<List<String>> getApplicableCountries(){
        return new ResponseEntity<>(centerRepository.findDistinctCountries(), HttpStatus.OK);
    }

    public ResponseEntity<List<Center>> getAllCenters(String country){
        List<Center> centers=null;
        if(StringUtils.isEmpty(country)){
            centers = centerRepository.findCentersByActive(AppConstants.ACTIVE.getValue());
        } else {
            centers = centerRepository.findCentersByCountryAndActive(country,AppConstants.ACTIVE.getValue());
        }
        return new ResponseEntity<>(centers,HttpStatus.OK);
    }

    public ResponseEntity<Center> getCenterDetails(String centerId){
        Center center= null;
        if(!StringUtils.isEmpty(centerId)){
            center= centerRepository.findCenterByCenterId(centerId);
        }
        return new ResponseEntity<>(center,HttpStatus.OK);
    }

    public ResponseEntity<String> createCenter(Center center){
        //create center
        center.setActive(AppConstants.ACTIVE.getValue());
        center.setAvailableSlots(center.getTotalSlots());
        Center savedEntity = centerRepository.save(center);
        //create slots
        slotService.createSlots(savedEntity,center.getTotalSlots());
        return getMessageResponseEntity(AppConstants.CENTER_CREATE_SUCCESSFULL.getValue(),HttpStatus.OK);
    }

    private ResponseEntity<String> getMessageResponseEntity(String message,HttpStatus status){
        return new ResponseEntity<>(message,status);
    }

    public ResponseEntity<String> modifyCenter(Center center){
        Center persistedCenter = centerRepository.findById(center.getCenterId()).get();
        center.setAvailableSlots(persistedCenter.getAvailableSlots());
        center.setTotalSlots(persistedCenter.getTotalSlots());
        center.setCountry(persistedCenter.getCountry());
        centerRepository.save(center);
        return getMessageResponseEntity(AppConstants.CENTER_MODIFY_SUCCESSFULL.getValue(),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCenter(String centerId){
        Center persistedCenter = centerRepository.findById(centerId).get();
        persistedCenter.setActive(AppConstants.INACTIVE.getValue());
        centerRepository.save(persistedCenter);
        slotService.deleteSlots(persistedCenter);
        return getMessageResponseEntity(AppConstants.CENTER_DELETE_SUCCESSFULL.getValue(),HttpStatus.OK);
    }

}
