package com.awadinhoo.code.drones.controllers;


import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.MedicationDTO;
import com.awadinhoo.code.drones.services.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medications")
public class MedicationController {


    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<MedicationDTO> createMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
        MedicationDTO createdMedication = medicationService.createMedication(medicationDTO);
        return new ResponseEntity<>(createdMedication, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicationDTO>> getAllMedications(){

        List<MedicationDTO> medications = medicationService.getAllMedications();
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }

    @GetMapping("/{medicationId}")
    public ResponseEntity<MedicationDTO> getMedicationById(@PathVariable("medicationId") Long medicationId){

        MedicationDTO medication = medicationService.getMedicationById(medicationId);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

    @PutMapping("/{medicationId}")
    public ResponseEntity<MedicationDTO> updateMedication(@PathVariable("medicationId") Long medicationId,
                                                          @RequestBody MedicationDTO medicationDTO){

        MedicationDTO updatedMedication = medicationService.updateMedication(medicationId, medicationDTO);
        return new ResponseEntity<>(updatedMedication, HttpStatus.OK);
    }

    @DeleteMapping("/{medicationId}")
    public ResponseEntity<String> deleteMedication(@PathVariable("medicationId") Long medicationId){

        medicationService.deleteMedication(medicationId);
        return new ResponseEntity<>(Constants.StatusMessages.MEDICATION_DELETED_SUCCESSFULLY_MESSAGE + medicationId, HttpStatus.OK);
    }
    
}
