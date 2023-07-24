package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dto.ParkingDTO;
import com.api.parkingcontrol.exception.ObjectNotFoundException;
import com.api.parkingcontrol.model.ParkingModel;
import com.api.parkingcontrol.repository.ParkingRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingServices {

    //inject point
    @Autowired
    ParkingRepository repo;

    @Transactional
    public ParkingModel save(ParkingModel obj) {
        return repo.save(obj);
    }

    public Optional<ParkingModel> findById(UUID id){
        return repo.findById(id);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return repo.existsByLicensePlateCar(licensePlateCar);
    }
    public boolean existsByApartmentAndBlock(String apartment, String block){
        return repo.existsByApartmentAndBlock(apartment,block);
    }
    public boolean existsByParkingSpotNumber(String spotNumber){
        return repo.existsByParkingSpotNumber(spotNumber);
    }

    public List<ParkingModel> findAll() {
        return repo.findAll();
    }
    @Transactional
    public void delete(ParkingModel id){
         repo.delete(id);
    }
}