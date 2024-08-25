package com.marzz.maintenance_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto {
    private String name;
    private String type;
    private Date acquisitionDate;
    private String status;
    private Date lastMaintenance;
    private int usefulLife;
    private int userId;
}
