package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
}
