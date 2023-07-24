package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.model.ParkingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingModel, String> {
}
