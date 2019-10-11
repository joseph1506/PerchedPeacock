package com.parking.maintenance.repositories;

import com.parking.maintenance.entity.Center;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CenterRepository extends CrudRepository<Center,String> {
    String DISTINCT_COUNTRY_FROM_CENTERS = "SELECT DISTINCT C.COUNTRY FROM CENTER C";

    @Query(value = DISTINCT_COUNTRY_FROM_CENTERS, nativeQuery = true)
    List<String> findDistinctCountries();

    List<Center> findCentersByCountryAndActive(String country,String active);

    List<Center> findCentersByActive(String active);

    Center findCenterByCenterId(String centerId);
}
