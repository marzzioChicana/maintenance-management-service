package com.marzz.maintenance_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto {
    private String name;
    private String type;
    private LocalDate acquisitionDate;
    private String status;
    private LocalDate lastMaintenance;
    private Integer usefulLife;
    private String photo;
    private Integer userId;
}
