package com.awadinhoo.code.drones.controllers;


import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.FleetRequestDTO;
import com.awadinhoo.code.drones.dtos.FleetResponseDTO;
import com.awadinhoo.code.drones.services.FleetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fleets")
public class FleetController {


    private final FleetService fleetService;

    @Autowired
    public FleetController(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    @PostMapping
    public ResponseEntity<FleetResponseDTO> createFleet(@Valid @RequestBody FleetRequestDTO fleetRequestDTO) {
        FleetResponseDTO createdFleet = fleetService.createFleet(fleetRequestDTO);
        return new ResponseEntity<>(createdFleet, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<FleetResponseDTO>> getAllFleets(){

        List<FleetResponseDTO> fleets = fleetService.getAllFleets();
        return new ResponseEntity<>(fleets, HttpStatus.OK);
    }

    @GetMapping("/{fleetId}")
    public ResponseEntity<FleetResponseDTO> getFleetById(@PathVariable("fleetId") Long fleetId){

        FleetResponseDTO fleet = fleetService.getFleetById(fleetId);
        return new ResponseEntity<>(fleet, HttpStatus.OK);
    }

    @PutMapping("/{fleetId}")
    public ResponseEntity<FleetResponseDTO> updateFleet(@PathVariable("fleetId") Long fleetId, @RequestBody FleetRequestDTO fleetRequestDTO){

        FleetResponseDTO updatedFleet = fleetService.updateFleet(fleetId, fleetRequestDTO);
        return new ResponseEntity<>(updatedFleet, HttpStatus.OK);
    }

    @DeleteMapping("/{fleetId}")
    public ResponseEntity<String> deleteFleet(@PathVariable("fleetId") Long fleetId){

        fleetService.deleteFleet(fleetId);
        return new ResponseEntity<>(Constants.StatusMessages.FLEET_DELETED_SUCCESSFULLY_MESSAGE + fleetId, HttpStatus.OK);
    }
    
}
