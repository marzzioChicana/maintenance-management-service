package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.Machine;
import com.marzz.maintenance_management_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Integer> {
    List<Machine> getMachinesByUser(User user);
}
