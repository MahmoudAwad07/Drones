package com.awadinhoo.code.drones.services.impl;

import com.awadinhoo.code.drones.entities.DroneAudit;
import com.awadinhoo.code.drones.repositories.DroneAuditRepository;
import com.awadinhoo.code.drones.services.DroneAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneAuditServiceImpl implements DroneAuditService {

    private final DroneAuditRepository droneAuditRepository;

    @Autowired
    public DroneAuditServiceImpl(DroneAuditRepository droneAuditRepository) {
        this.droneAuditRepository = droneAuditRepository;
    }

    @Override
    public void saveDroneAudits(List<DroneAudit> droneAudits) {
        droneAuditRepository.saveAll(droneAudits);
    }
}
