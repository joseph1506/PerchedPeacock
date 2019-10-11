package com.parking.maintenance.service;

import com.parking.maintenance.constants.AppConstants;
import com.parking.maintenance.entity.Country;
import com.parking.maintenance.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    private List<Country> getAllCountries(){
        return (List<Country>) countryRepository.findAll();
    }

    public ResponseEntity<List<Country>> getActiveCountries() {
        List<Country> activeCountries = this.getAllCountries().stream()
                .filter(c-> AppConstants.ACTIVE.getValue().equalsIgnoreCase(c.getActive()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(activeCountries, HttpStatus.OK);
    }

}
