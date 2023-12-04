package com.awadinhoo.code.drones.services;

import com.awadinhoo.code.drones.entities.DroneAudit;

import java.util.List;

public interface DroneAuditService {

    void saveDroneAudits(List<DroneAudit> droneAudits);
}
