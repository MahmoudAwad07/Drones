package com.awadinhoo.code.drones.services;


import com.awadinhoo.code.drones.dtos.FleetRequestDTO;
import com.awadinhoo.code.drones.dtos.FleetResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FleetService {
    FleetResponseDTO createFleet(FleetRequestDTO fleetRequestDTO);
    FleetResponseDTO updateFleet(Long fleetId, FleetRequestDTO fleetRequestDTO);
    void deleteFleet(Long fleetId);
    FleetResponseDTO getFleetById(Long fleetId);
    List<FleetResponseDTO> getAllFleets();
}
