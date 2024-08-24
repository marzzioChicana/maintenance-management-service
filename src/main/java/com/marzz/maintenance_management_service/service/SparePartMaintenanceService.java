package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.model.SparePartMaintenance;

import java.util.List;

public interface SparePartMaintenanceService {
    public abstract SparePartMaintenance createSparePartMaintenance(SparePartMaintenance sparePartMaintenance);
    public abstract void updateSparePartMaintenance(SparePartMaintenance sparePartMaintenance);
    public abstract void deleteSparePartMaintenance(SparePartMaintenance sparePartMaintenance);
    public abstract SparePartMaintenance getSparePartMaintenanceById(int id);
    public abstract List<SparePartMaintenance> getSparePartMaintenancesByMaintenanceId(int maintenanceId);
    public abstract List<SparePartMaintenance> getSparePartMaintenances();
}
