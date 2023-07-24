package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.model.ParkingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingModel, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String spotNumber);
    boolean existsByApartmentAndBlock(String apartment,String block);

}
