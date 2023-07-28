package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.dto.ParkingDTO;
import com.api.parkingcontrol.model.ParkingModel;
import com.api.parkingcontrol.services.ParkingServices;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    ParkingServices services;

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingDTO parkingDTO) {
        if (services.existsByLicensePlateCar(parkingDTO.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use");
        }
        if (services.existsByParkingSpotNumber(parkingDTO.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use");

        }
        if (services.existsByApartmentAndBlock(parkingDTO.getApartment(), parkingDTO.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered fot this apartament/block");
        }
        var parkingModel = new ParkingModel();
        //conversão de dto para model
        BeanUtils.copyProperties(parkingDTO, parkingModel);
        parkingModel.setDateRegistration(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(parkingModel));
    }

    @GetMapping
    public ResponseEntity<List<ParkingModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id){
        Optional<ParkingModel> parkingModelOptional = services.findById(id);
        if (!parkingModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found 404");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<ParkingModel> parkingModelOptional = services.findById(id);
        if(!parkingModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found 404");
        }
        services.delete(parkingModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("id deleted OK");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> putUpdate(@PathVariable(value = "id")UUID id,
                                            @RequestBody @Valid ParkingDTO dto){
        Optional<ParkingModel> parkingModelOptional = services.findById(id);
        if(!parkingModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found 404");
        }
        var parkingModel = new ParkingModel();
        BeanUtils.copyProperties(dto, parkingModel);
        parkingModel.setId(parkingModelOptional.get().getId());
        parkingModel.setDateRegistration(parkingModelOptional.get().getDateRegistration());
        return ResponseEntity.status(HttpStatus.OK).body(services.save(parkingModel));
    }


}