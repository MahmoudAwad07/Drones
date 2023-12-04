package com.awadinhoo.code.drones.controllers;


import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.DroneBatteryLevelDTO;
import com.awadinhoo.code.drones.dtos.DroneDTO;
import com.awadinhoo.code.drones.dtos.FleetResponseDTO;
import com.awadinhoo.code.drones.services.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drones")
public class DroneController {

    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {

        DroneDTO registeredDrone = droneService.registerDrone(droneDTO);
        return new ResponseEntity<>(registeredDrone, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<DroneDTO>> getAllDrones(){

        List<DroneDTO> allDrones = droneService.getAllDrones();
        return new ResponseEntity<>(allDrones, HttpStatus.OK);
    }

    @GetMapping("fleet/{fleetId}")
    public ResponseEntity<List<DroneDTO>> getDronesByFleetId(@PathVariable("fleetId") Long fleetId){

        List<DroneDTO> drones = droneService.getDronesByFleetId(fleetId);
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

    @GetMapping("/{droneId}")
    public ResponseEntity<DroneDTO> getDroneById(@PathVariable("droneId") Long droneId){

        DroneDTO drone = droneService.getDroneById(droneId);
        return new ResponseEntity<>(drone, HttpStatus.OK);
    }

    @PutMapping("/{droneId}")
    public ResponseEntity<DroneDTO> updateDrone(@PathVariable("droneId") Long droneId, @RequestBody DroneDTO droneDTO){

        DroneDTO updatedDrone = droneService.updateDrone(droneId, droneDTO);
        return new ResponseEntity<>(updatedDrone, HttpStatus.OK);
    }

    @DeleteMapping("/{droneId}")
    public ResponseEntity<String> deleteDrone(@PathVariable("droneId") Long droneId){

        droneService.deleteDrone(droneId);
        return new ResponseEntity<>(Constants.StatusMessages.DRONE_DELETED_SUCCESSFULLY_MESSAGE + droneId, HttpStatus.OK);
    }


    @GetMapping("batteryLevel/{droneId}")
    public ResponseEntity<DroneBatteryLevelDTO> getDroneBatteryLevelById(@PathVariable("droneId") Long droneId){

        DroneBatteryLevelDTO droneBatteryLevel = droneService.getDroneBatterLevelById(droneId);
        return new ResponseEntity<>(droneBatteryLevel, HttpStatus.OK);
    }

    @GetMapping("availableToDeliverByFleet/{fleetId}")
    public ResponseEntity<List<DroneDTO>> getAvailableDronesToDeliverByFleetId(@PathVariable("fleetId") Long fleetId){

        List<DroneDTO> drones = droneService.getAvailableDronesToDeliverByFleetId(fleetId);
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

    @GetMapping("availableToDeliverByZone/{zoneId}")
    public ResponseEntity<List<DroneDTO>> getAvailableDronesToDeliverByZoneId(@PathVariable("zoneId") Long zoneId){

        List<DroneDTO> drones = droneService.getAvailableDronesToDeliverByZoneId(zoneId);
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

}
