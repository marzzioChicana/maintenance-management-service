package com.marzz.maintenance_management_service.controller;

import com.marzz.maintenance_management_service.dto.MachineUpdateDto;
import com.marzz.maintenance_management_service.dto.MaintenanceDto;
import com.marzz.maintenance_management_service.exception.ValidationException;
import com.marzz.maintenance_management_service.model.Maintenance;
import com.marzz.maintenance_management_service.service.MachineService;
import com.marzz.maintenance_management_service.service.MaintenanceService;
import com.marzz.maintenance_management_service.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping("/api/maintenance/service/v1/maintenances")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    public MaintenanceController() { }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Maintenance>> getMaintenancesByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(maintenanceService.getMaintenancesByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody MaintenanceDto maintenanceDto) {
        validateMaintenance(maintenanceDto);

        return new ResponseEntity<>(maintenanceService.createMaintenance(maintenanceDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{machineId}")
    public ResponseEntity<Object> deleteMaintenance(@PathVariable int machineId) {
        maintenanceService.deleteMaintenancesByMachineId(machineId);
        return new ResponseEntity<>("Maintenance deleted successfully", HttpStatus.OK);
    }

    private void validateMaintenance(MaintenanceDto maintenanceDto) {
        if (maintenanceDto.getCost() == null) {
            throw new ValidationException("Cost is required");
        }

        if (maintenanceDto.getDescription() == null || maintenanceDto.getDescription().trim().isEmpty()) {
            throw new ValidationException("Description is required");
        }

        if (maintenanceDto.getQuantity() == null) {
            throw new ValidationException("Quantity is required");
        }

        if (maintenanceDto.getMachineId() == null) {
            throw new ValidationException("Machine id is required");
        }

        if (maintenanceDto.getSparePartId() == null) {
            throw new ValidationException("Spare part id is required");
        }
    }
}
