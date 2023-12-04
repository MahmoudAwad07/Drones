package com.awadinhoo.code.drones.repositories;

import com.awadinhoo.code.drones.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Optional<Zone> findByZoneIdAndIsDeleted(Long zoneId, Integer isDeleted);
    Optional<Zone> findByNameAndIsDeleted(String name, Integer isDeleted);
    Optional<List<Zone>> findAllByIsDeleted(Integer isDeleted);
    Optional<List<Zone>> findAllByZoneIdInAndIsDeleted(List<Long> zoneIds , Integer isDeleted);
    @Query("SELECT z FROM Zone z WHERE z.zoneId In :zoneIds AND z.isDeleted = :isDeleted AND z.active = :isActive And z.fleet.fleetId IS NULL")
    Optional<List<Zone>> findAllByZoneIdAndIsDeletedAndIsActive(List<Long> zoneIds , Integer isActive ,  Integer isDeleted);
    @Query("SELECT z FROM Zone z WHERE z.zoneId In :zoneIds AND z.isDeleted = :isDeleted AND z.active = :isActive And ( z.fleet.fleetId IS NULL OR z.fleet.fleetId = :fleetId )")
    Optional<List<Zone>> findAllByZoneIdAndIsDeletedAndIsActiveAndFleetId(List<Long> zoneIds , Integer isActive ,  Integer isDeleted, Long fleetId);
}
