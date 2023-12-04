package com.awadinhoo.code.drones.services.impl;

import com.awadinhoo.code.drones.dtos.MedicationDTO;
import com.awadinhoo.code.drones.entities.Medication;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.mappers.MedicationMapper;
import com.awadinhoo.code.drones.repositories.MedicationRepository;
import com.awadinhoo.code.drones.services.MedicationService;
import com.awadinhoo.code.drones.validation.MedicationValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {


    private final MedicationRepository medicationRepository;

    private final MedicationMapper medicationMapper;

    private final MedicationValidationService medicationValidationService;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository,
                                 MedicationMapper medicationMapper,
                                 MedicationValidationService medicationValidationService) {
        this.medicationRepository = medicationRepository;
        this.medicationMapper = medicationMapper;
        this.medicationValidationService = medicationValidationService;
    }

    @Override
    public MedicationDTO createMedication(MedicationDTO medicationDTO) {

        medicationValidationService.checkMedicationExistsByNameOrCode(medicationDTO.getName() , medicationDTO.getCode());
        Medication savedMedication = medicationRepository.save(medicationMapper.getMedicationEntityFromDTO(medicationDTO));
        return medicationMapper.getMedicationDTOFromEntity(savedMedication);
    }

    @Override
    public MedicationDTO updateMedication(Long medicationId, MedicationDTO updatedMedicationDTO) {

        medicationValidationService.checkMedicationExistsById(medicationId);
        medicationValidationService.checkMedicationNameOrCodeIsAlreadyTaken(updatedMedicationDTO.getName(), updatedMedicationDTO.getCode(), medicationId);
        Medication updatedMedication = medicationRepository.save(medicationMapper.getMedicationEntityFromDTO(updatedMedicationDTO));
        return medicationMapper.getMedicationDTOFromEntity(updatedMedication);
    }

    @Override
    public void deleteMedication(Long medicationId) {

        Medication medication = medicationValidationService.checkAndGetIfFound(medicationId);
        medication.setIsDeleted(DeleteStatus.DELETED.getStatusId());
        medicationRepository.save(medication);
    }

    @Override
    public MedicationDTO getMedicationById(Long medicationId) {

        Medication medication = medicationValidationService.checkAndGetIfFound(medicationId);
        return medicationMapper.getMedicationDTOFromEntity(medication);

    }

    @Override
    public List<MedicationDTO> getAllMedications() {

        List<Medication> allMedications = medicationRepository.findAllByIsDeleted(DeleteStatus.NOT_DELETED.getStatusId()).orElseGet(ArrayList::new);
        return medicationMapper.getListMedicationDTOFromEntities(allMedications);
    }
}
