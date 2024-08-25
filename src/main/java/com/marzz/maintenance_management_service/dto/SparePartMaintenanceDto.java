package com.marzz.maintenance_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparePartMaintenanceDto {
    private int maintenanceId;
    private int sparePartId;
    private int quantity;
}
