package com.parking.booking.repositories;

import com.parking.booking.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface BookingRepository extends CrudRepository<Booking,String> {
    List<Booking> findBookingsByUserIdAndStatus(String userId,String status);

    List<Booking> findBookingsByUserIdAndStatusIsNot(String userId,String status);

    List<Booking> findBookingsByUserId(String userId);

    List<Booking> findBookingsBySlotIdAndStatusAndFromTimeIsAfterAndFromTimeIsBefore(String slotId, String status, Date fromDate,Date toDate);
}
