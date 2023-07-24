package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.services.ParkingServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingController{

    final ParkingServices services;

    public ParkingController(ParkingServices services) {
        this.services = services;
    }


}