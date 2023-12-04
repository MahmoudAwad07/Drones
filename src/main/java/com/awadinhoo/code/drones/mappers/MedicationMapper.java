package com.awadinhoo.code.drones.mappers;

import com.awadinhoo.code.drones.dtos.MedicationDTO;
import com.awadinhoo.code.drones.entities.Medication;
import org.mapstruct.*;

import java.util.List;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MedicationMapper {

    MedicationDTO getMedicationDTOFromEntity(Medication medicationEntity);
    Medication getMedicationEntityFromDTO(MedicationDTO medicationDTO);
    List<MedicationDTO> getListMedicationDTOFromEntities(List<Medication> medicationList);
    List<Medication> getListMedicationEntityFromDTOs(List<MedicationDTO> medicationDTOList);
}
