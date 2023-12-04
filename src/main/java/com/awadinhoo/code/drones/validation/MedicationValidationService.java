package com.awadinhoo.code.drones.validation;


import com.awadinhoo.code.drones.dtos.OrderMedicationDTO;
import com.awadinhoo.code.drones.entities.Medication;

import java.util.List;
import java.util.Map;

public interface MedicationValidationService {
    void checkMedicationExistsByNameOrCode(String name, String code);
    void checkMedicationExistsById(Long medicationId);
    void checkMedicationNameOrCodeIsAlreadyTaken(String name , String code , Long medicationId);
    void checkMedicationQuantity(Long medicationId);
    Medication checkAndGetIfFound(Long medicationId);
    Map<Long, Medication> checkMedicationsAndGet(List<Long> medicationsIds);
    void checkMedicationQuantity(Map<Long, Medication> medications, List<OrderMedicationDTO> orderMedicationDTOS);
}
