package com.parking.maintenance.repositories;

import com.parking.maintenance.entity.Country;
import com.parking.maintenance.entity.Slot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface SlotRepository extends CrudRepository<Slot,String> {

    List<Slot> findSlotsByCenterId(String centerId);

    List<Slot> findSlotsByCenterIdAndStatus(String centerId,String status);

    List<Slot> findSlotsByCenterIdAndStatusNot(String centerId,String status);

}
