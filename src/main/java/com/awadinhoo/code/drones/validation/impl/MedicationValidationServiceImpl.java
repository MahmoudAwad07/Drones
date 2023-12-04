package com.awadinhoo.code.drones.validation.impl;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.OrderMedicationDTO;
import com.awadinhoo.code.drones.entities.Medication;
import com.awadinhoo.code.drones.enums.ActiveStatus;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.drones.repositories.MedicationRepository;
import com.awadinhoo.code.drones.validation.MedicationValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationValidationServiceImpl implements MedicationValidationService {


    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationValidationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Boolean checkMedicationExistsByName(String name) {
        return medicationRepository.findByNameAndIsDeleted(name, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();
    }

    @Override
    public void checkMedicationExistsByNameOrCode(String name, String code) {
        boolean medicationExists = medicationRepository.findByNameOrCodeAndIsDeleted(name, code, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(medicationExists){
            throw new DronesCustomException(Constants.StatusMessages.MEDICATION_ALREADY_EXIST_BY_NAME_CODE_MESSAGE);
        }
    }

    @Override
    public void checkMedicationExistsById(Long medicationId) {
        boolean medicationExists = medicationRepository.findByMedicationIdAndIsDeleted(medicationId, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(!medicationExists){
            throw new ResourceNotFoundException(Constants.StatusMessages.MEDICATION_NOT_FOUND_MESSAGE + medicationId);
        }
    }

    @Override
    public void checkMedicationNameOrCodeIsAlreadyTaken(String name, String code , Long medicationId) {

        Optional<Medication> optionalMedication = medicationRepository.findByNameOrCodeAndIsDeleted(name, code, DeleteStatus.NOT_DELETED.getStatusId());

        if(optionalMedication.isPresent() && !optionalMedication.get().getMedicationId().equals(medicationId)){
            throw new DronesCustomException(Constants.StatusMessages.MEDICATION_ALREADY_EXIST_BY_NAME_CODE_MESSAGE);
        }
    }

    // TODO : Check if you will use the below method
    @Override
    public void checkMedicationQuantity(Long id) {
        boolean quantityFound = medicationRepository.findByMedicationIdAndIsDeleted(id, DeleteStatus.NOT_DELETED.getStatusId()).get().getQuantity() > 0;

        if(!quantityFound){
            throw new DronesCustomException(Constants.StatusMessages.MEDICATION_STOCK_NOT_FOUND_MESSAGE + id);
        }
    }

    @Override
    public Medication checkAndGetIfFound(Long medicationId) {

        Optional<Medication> medicationOptional = medicationRepository.findByMedicationIdAndIsDeleted(medicationId, DeleteStatus.NOT_DELETED.getStatusId());

        if(medicationOptional.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.MEDICATION_NOT_FOUND_MESSAGE + medicationId);
        }

        return medicationOptional.get();
    }

    @Override
    public Map<Long, Medication> checkMedicationsAndGet(List<Long> medicationsIds) {

        Optional<List<Medication>> optionalMedications = medicationRepository.findAllByMedicationIdInAndActiveAndIsDeleted(medicationsIds, ActiveStatus.ACTIVE.getStatusId(), DeleteStatus.NOT_DELETED.getStatusId());

        if(optionalMedications.isEmpty()){
            throw new DronesCustomException(Constants.StatusMessages.MEDICATIONS_ARE_NOT_FOUND_MESSAGE);
        }

        List<Medication> medications = optionalMedications.get();

        if(medications.size() != medicationsIds.size()){
            throw new DronesCustomException(Constants.StatusMessages.ONE_OR_MORE_THAN_ONE_OF_MEDICATIONS_ARE_NOT_FOUND_MESSAGE);
        }

        return medications.stream().collect(Collectors.toMap(Medication::getMedicationId, medication -> medication));
    }

    @Override
    public void checkMedicationQuantity(Map<Long, Medication> medications, List<OrderMedicationDTO> orderMedicationDTOS) {

        orderMedicationDTOS.forEach( orderMedicationDTO -> {
            Long medicationId = orderMedicationDTO.getMedicationId();
            Medication medication = medications.get(medicationId);
            int newQuantity = medication.getQuantity() - orderMedicationDTO.getQuantity();
            if( newQuantity < 0 ){
                throw new DronesCustomException(Constants.StatusMessages.MEDICATION_STOCK_NOT_FOUND_MESSAGE + medicationId );
            }
            medication.setQuantity(newQuantity);
            medications.put(medicationId, medication);
        });
    }


}
