package com.parking.maintenance.repositories;

import com.parking.maintenance.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CountryRepository extends CrudRepository<Country,String> {
}
