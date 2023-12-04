package com.awadinhoo.code.drones.services;


import com.awadinhoo.code.drones.dtos.MedicationDTO;

import java.util.List;

public interface MedicationService {
    MedicationDTO createMedication(MedicationDTO medicationDTO);
    MedicationDTO updateMedication(Long medicationId, MedicationDTO updatedMedicationDTO);
    void deleteMedication(Long medicationId);
    MedicationDTO getMedicationById(Long medicationId);
    List<MedicationDTO> getAllMedications();
}
