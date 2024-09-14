package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.dto.MaintenanceDto;
import com.marzz.maintenance_management_service.model.Maintenance;

import java.util.List;

public interface MaintenanceService {
    public abstract Maintenance createMaintenance(MaintenanceDto maintenanceDto);
    public abstract void updateMaintenance(int id, MaintenanceDto maintenanceDto);
    public abstract void deleteMaintenance(int id);
    public abstract void deleteMaintenancesByMachineId(int machineId);
    public abstract Maintenance getMaintenanceById(int id);
    public abstract List<Maintenance> getMaintenances();
    public abstract List<Maintenance> getMaintenancesByUserId(int userId);
    public abstract boolean isMaintenanceExist(int id);
}
