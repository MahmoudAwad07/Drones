package com.awadinhoo.code.drones.repositories;


import com.awadinhoo.code.drones.entities.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Long> {
    Optional<Fleet> findByFleetIdAndIsDeleted(Long id , Integer isDeleted);
    Optional<Fleet> findByNameAndIsDeleted(String name, Integer isDeleted);
    Optional<List<Fleet>> findAllByIsDeleted(Integer isDeleted);
}
