package com.parking.user.repositories;

import com.parking.user.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle,String> {

    List<Vehicle> findVehiclesByActive(String active);

    Vehicle findVehicleByRegistrationIdAndUserId(String registrationId,String userId);

    Vehicle findVehicleByRegistrationIdAndUserIdAndIdNot(String registrationId,String userId,String id);
}
