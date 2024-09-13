package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.dto.MachineDto;
import com.marzz.maintenance_management_service.dto.MachineUpdateDto;
import com.marzz.maintenance_management_service.model.Machine;

import java.util.List;

public interface MachineService {
    public abstract Machine createMachine(MachineDto machineDto);
    public abstract void updateMachine(MachineUpdateDto machineUpdateDto);
    public abstract void deleteMachine(int id);
    public abstract Machine getMachineById(int id);
    public abstract List<Machine> getMachines();
    public abstract List<Machine> getMachinesByUserId(int userId);
    public abstract boolean isMachineExist(int id);
}
