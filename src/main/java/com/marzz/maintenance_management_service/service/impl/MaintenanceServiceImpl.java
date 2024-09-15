package com.marzz.maintenance_management_service.service.impl;

import com.marzz.maintenance_management_service.dto.MaintenanceDto;
import com.marzz.maintenance_management_service.exception.ResourceNotFoundException;
import com.marzz.maintenance_management_service.model.Machine;
import com.marzz.maintenance_management_service.model.Maintenance;
import com.marzz.maintenance_management_service.model.SparePart;
import com.marzz.maintenance_management_service.model.User;
import com.marzz.maintenance_management_service.repository.MachineRepository;
import com.marzz.maintenance_management_service.repository.MaintenanceRepository;
import com.marzz.maintenance_management_service.repository.SparePartRepository;
import com.marzz.maintenance_management_service.repository.UserRepository;
import com.marzz.maintenance_management_service.service.MaintenanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MachineRepository machineRepository;
    private final SparePartRepository sparePartRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, MachineRepository machineRepository, SparePartRepository sparePartRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.machineRepository = machineRepository;
        this.sparePartRepository = sparePartRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Maintenance createMaintenance(MaintenanceDto maintenanceDto) {
        Maintenance maintenance = DtoToEntity(maintenanceDto);

        Machine machine = machineRepository.findById(maintenanceDto.getMachineId())
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id :: " + maintenanceDto.getMachineId()));

        SparePart sparePart = sparePartRepository.findById(maintenanceDto.getSparePartId())
                .orElseThrow(() -> new ResourceNotFoundException("SparePart not found for this id :: " + maintenanceDto.getSparePartId()));

        maintenance.setMachine(machine);
        maintenance.setSparePart(sparePart);
        maintenance.setDate(LocalDate.now());
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void updateMaintenance(int id, MaintenanceDto maintenanceDto) {
        Maintenance maintenance = DtoToEntity(maintenanceDto);

        Machine machine = machineRepository.findById(maintenanceDto.getMachineId())
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id :: " + maintenanceDto.getMachineId()));

        SparePart sparePart = sparePartRepository.findById(maintenanceDto.getSparePartId())
                .orElseThrow(() -> new ResourceNotFoundException("SparePart not found for this id :: " + maintenanceDto.getSparePartId()));

        maintenance.setId(id);
        maintenance.setMachine(machine);
        maintenance.setSparePart(sparePart);
        maintenanceRepository.save(maintenance);
    }

    @Override
    public void deleteMaintenance(int id) {
        maintenanceRepository.deleteById(id);
    }

    @Override
    public void deleteMaintenancesByMachineId(int machineId) {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id :: " + machineId));

        List<Maintenance> maintenances = maintenanceRepository.getMaintenanceByMachine(machine);

        if(maintenances.isEmpty()) {
            return;
        }

        maintenanceRepository.deleteAll(maintenances);
    }

    @Override
    public Maintenance getMaintenanceById(int id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance not found for this id :: " + id));
    }

    @Override
    public List<Maintenance> getMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public List<Maintenance> getMaintenancesByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        List<Machine> machines = machineRepository.getMachinesByUser(user);

        if(machines.isEmpty()) {
            return Collections.emptyList();
        }

        return maintenanceRepository.getMaintenanceByMachineIn(machines);
    }

    @Override
    public boolean isMaintenanceExist(int id) {
        return maintenanceRepository.existsById(id);
    }

    private MaintenanceDto EntityToDto(Maintenance maintenance) { return modelMapper.map(maintenance, MaintenanceDto.class); }

    private Maintenance DtoToEntity(MaintenanceDto maintenanceDto) {
        return modelMapper.map(maintenanceDto, Maintenance.class);
    }
}
