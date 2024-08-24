package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.SparePartMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartMaintenanceRepository extends JpaRepository<SparePartMaintenance, Integer> {
}
