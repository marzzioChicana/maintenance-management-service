package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.dto.MachineDTO;
import com.marzz.maintenance_management_service.model.Machine;

import java.util.List;

public interface MachineService {
    public abstract Machine createMachine(MachineDTO machineDTO);
    public abstract void updateMachine(MachineDTO machineDTO);
    public abstract void deleteMachine(int id);
    public abstract Machine getMachineById(int id);
    public abstract List<Machine> getMachines();
}
