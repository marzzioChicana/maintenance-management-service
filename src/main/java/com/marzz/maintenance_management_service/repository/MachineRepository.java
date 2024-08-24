package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Integer> {
}
