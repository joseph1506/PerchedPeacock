package com.parking.maintenance.controller;

import com.parking.maintenance.entity.Country;
import com.parking.maintenance.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryResource {

    @Autowired
    private CountryService countryService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getActiveCountries(){
        return countryService.getActiveCountries();
    }

}
