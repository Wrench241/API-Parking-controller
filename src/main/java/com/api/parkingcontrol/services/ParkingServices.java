package com.api.parkingcontrol.services;

import com.api.parkingcontrol.repository.ParkingRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingServices {

    //inject point
    final ParkingRepository repo;

    public ParkingServices(ParkingRepository repo) {
        this.repo = repo;
    }
}
