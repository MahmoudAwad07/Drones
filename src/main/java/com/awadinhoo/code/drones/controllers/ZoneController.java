package com.awadinhoo.code.drones.controllers;


import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.ZoneDTO;
import com.awadinhoo.code.drones.services.ZoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/zones")
public class ZoneController {

    private final ZoneService zoneService;

    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ResponseEntity<ZoneDTO> createZone(@Valid @RequestBody ZoneDTO zoneDTO) {
        ZoneDTO createdZone = zoneService.createZone(zoneDTO);
        return new ResponseEntity<>(createdZone, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ZoneDTO>> getAllZones(){

        List<ZoneDTO> zones = zoneService.getAllZones();
        return new ResponseEntity<>(zones, HttpStatus.OK);
    }

    @GetMapping("/{zoneId}")
    public ResponseEntity<ZoneDTO> getZoneById(@PathVariable("zoneId") Long zoneId){

        ZoneDTO zone = zoneService.getZoneById(zoneId);
        return new ResponseEntity<>(zone, HttpStatus.OK);
    }


    @PutMapping("/{zoneId}")
    public ResponseEntity<ZoneDTO> updateZone(@PathVariable("zoneId") Long zoneId, @RequestBody ZoneDTO zoneDTO){

        ZoneDTO updatedZone = zoneService.updateZone(zoneId, zoneDTO);
        return new ResponseEntity<>(updatedZone, HttpStatus.OK);
    }

    @DeleteMapping("/{zoneId}")
    public ResponseEntity<String> deleteZone(@PathVariable("zoneId") Long zoneId){

        zoneService.deleteZone(zoneId);
        return new ResponseEntity<>(Constants.StatusMessages.ZONE_DELETED_SUCCESSFULLY_MESSAGE + zoneId, HttpStatus.OK);
    }


}
