package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.Machine;
import com.marzz.maintenance_management_service.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    /*
    @Query("SELECT m FROM Maintenance m WHERE m.machine.user.id = :userId")
    List<Maintenance> findAllByUserId(@Param("userId") int userId);
    */

    List<Maintenance> getMaintenanceByMachineIn(List<Machine> machines);
    List<Maintenance> getMaintenanceByMachine(Machine machine);
}
