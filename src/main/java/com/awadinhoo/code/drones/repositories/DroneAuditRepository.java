package com.awadinhoo.code.drones.repositories;

import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.DroneAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneAuditRepository extends JpaRepository<DroneAudit, Long> {

}
