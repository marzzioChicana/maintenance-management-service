package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.model.Maintenance;

import java.util.List;

public interface MaintenanceService {
    public abstract Maintenance createMaintenance(Maintenance maintenance);
    public abstract void updateMaintenance(Maintenance maintenance);
    public abstract void deleteMaintenance(Maintenance maintenance);
    public abstract Maintenance getMaintenanceById(int id);
    public abstract List<Maintenance> getMaintenances();
}
