package com.awadinhoo.code.drones.repositories;


import com.awadinhoo.code.drones.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MedicationRepository extends JpaRepository<Medication , Long> {

    Optional<Medication> findByMedicationIdAndIsDeleted(Long medicationId , Integer isDeleted);
    Optional<Medication> findByNameOrCodeAndIsDeleted(String name, String code, Integer isDeleted);
    Optional<Medication> findByNameAndIsDeleted(String name, Integer isDeleted);
    Optional<List<Medication>> findAllByIsDeleted(Integer isDeleted);
    Optional<List<Medication>> findAllByMedicationIdInAndActiveAndIsDeleted(List<Long> medicationIds, Integer isActive, Integer isDeleted);

}
