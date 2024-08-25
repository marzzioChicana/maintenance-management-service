package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.dto.MachineDto;
import com.marzz.maintenance_management_service.model.Machine;

import java.util.List;

public interface MachineService {
    public abstract Machine createMachine(MachineDto machineDTO);
    public abstract void updateMachine(MachineDto machineDTO);
    public abstract void deleteMachine(int id);
    public abstract Machine getMachineById(int id);
    public abstract List<Machine> getMachines();
}
