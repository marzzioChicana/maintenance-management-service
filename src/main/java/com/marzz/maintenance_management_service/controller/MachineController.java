package com.marzz.maintenance_management_service.controller;

import com.marzz.maintenance_management_service.dto.MachineDto;
import com.marzz.maintenance_management_service.exception.ValidationException;
import com.marzz.maintenance_management_service.model.Machine;
import com.marzz.maintenance_management_service.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping("/api/maintenance/service/v1/machines")
public class MachineController {
    @Autowired
    private MachineService machineService;

    public MachineController() { }

    @GetMapping
    public ResponseEntity<List<Machine>> getAllMachines() {
        return new ResponseEntity<>(machineService.getMachines(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable int id) {
        return new ResponseEntity<>(machineService.getMachineById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Machine> createMachine(@RequestBody MachineDto machineDto) {
        validateMachine(machineDto);
        return new ResponseEntity<>(machineService.createMachine(machineDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Machine> updateMachine(@RequestBody MachineDto machineDto) {
        validateMachine(machineDto);
        machineService.updateMachine(machineDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMachine(@PathVariable int id) {
        machineService.deleteMachine(id);
        return new ResponseEntity<>("Machine deleted successfully", HttpStatus.OK);
    }

    private void validateMachine(MachineDto machineDto) {
        if(machineDto.getName() == null || machineDto.getName().trim().isEmpty()){
            throw new ValidationException("Name must be mandatory");
        }

        if(machineDto.getType() == null || machineDto.getType().trim().isEmpty()){
            throw new ValidationException("Type must be mandatory");
        }

        if(machineDto.getAcquisitionDate() == null){
            throw new ValidationException("Acquisition date must be mandatory");
        }

        if(machineDto.getStatus() == null || machineDto.getStatus().trim().isEmpty()){
            throw new ValidationException("Status must be mandatory");
        }

        if(machineDto.getUsefulLife().describeConstable().isEmpty()){
            throw new ValidationException("Useful life must be mandatory");
        }

        if(machineDto.getPhoto() == null || machineDto.getPhoto().trim().isEmpty()){
            throw new ValidationException("Photo must be mandatory");
        }

        if(machineDto.getUserId().describeConstable().isEmpty()){
            throw new ValidationException("User id must be mandatory");
        }
    }
}
