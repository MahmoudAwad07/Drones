package com.awadinhoo.code.drones.repositories;

import com.awadinhoo.code.drones.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository <Drone , Long>  {

    Optional<Drone> findByDroneIdAndIsDeleted(Long droneId , Integer isDeleted);
    Optional<Drone> findBySerialNumberAndIsDeleted(String serialNumber, Integer isDeleted);
    Optional<List<Drone>> findAllBySerialNumberAndIsDeleted(String serialNumber, Integer isDeleted);
    Optional<List<Drone>> findAllByIsDeleted(Integer isDeleted);
}
