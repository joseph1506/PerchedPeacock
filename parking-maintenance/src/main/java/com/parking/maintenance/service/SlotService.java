package com.parking.maintenance.service;

import com.parking.maintenance.constants.AppConstants;
import com.parking.maintenance.entity.Center;
import com.parking.maintenance.entity.Slot;
import com.parking.maintenance.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public ResponseEntity<List<Slot>> getAllParkingSlots(String centerId){
        return new ResponseEntity<>(slotRepository.findSlotsByCenterId(centerId), HttpStatus.OK);
    }

    public ResponseEntity<List<Slot>> getAllAvailableParkingSlots(String centerId){
        return new ResponseEntity<>(slotRepository.findSlotsByCenterIdAndStatus(centerId,
                AppConstants.AVAILABLE.getValue()),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteSlot(String slotId){
        Optional<Slot> slotOptional= slotRepository.findById(slotId);
        if(slotOptional.isPresent()){
            Slot slot= slotOptional.get();
            slot.setStatus(AppConstants.DELETED.getValue());
            slotRepository.save(slot);
            //cancel bookings for the slot

        }
        return getMessageResponseEntity(AppConstants.SLOT_DELETE_SUCCESSFULL.getValue(),HttpStatus.OK);
    }

    private ResponseEntity<String> getMessageResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message,status);
    }

    public void createSlots(Center center, long totalSlots){
        LongStream.range(1,totalSlots+1)
                .forEach(slotNo-> createSlot(center.getCenterId(),slotNo));
    }

    public void createSlot(String centerId, long slotNo) {
        Slot slot = new Slot();
        slot.setCenterId(centerId);
        slot.setSlotNo(slotNo);
        slot.setSlotName(AppConstants.SLOT_PREFIX.getValue()+slotNo);
        slot.setStatus(AppConstants.AVAILABLE.getValue());
        slotRepository.save(slot);
    }

    public void deleteSlots(Center center){
        List<Slot> activeSlotsForCenter = slotRepository.
                findSlotsByCenterIdAndStatusIsNot(center.getCenterId(),AppConstants.DELETED.getValue());
        activeSlotsForCenter.stream()
                .forEach(slot -> deleteSlot(slot.getSlotId()));
    }

    public ResponseEntity<String> addSlotsToCenter(String centerId, long noOfSlots){
        List<Slot> slots= slotRepository.findSlotsByCenterId(centerId);
        long maxSlotNo = slots.stream()
                .mapToLong(slot-> slot.getSlotNo())
                .max()
                .getAsLong();

        LongStream.range(maxSlotNo+1,maxSlotNo+noOfSlots+1)
                    .forEach(slotNo-> createSlot(centerId,slotNo));
        return getMessageResponseEntity(AppConstants.SLOT_ADDITION_SUCCESSFULL.getValue(),HttpStatus.OK);
    }
}
