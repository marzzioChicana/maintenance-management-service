package com.marzz.maintenance_management_service.service.impl;

import com.marzz.maintenance_management_service.dto.MachineDto;
import com.marzz.maintenance_management_service.exception.ResourceNotFoundException;
import com.marzz.maintenance_management_service.model.Machine;
import com.marzz.maintenance_management_service.model.User;
import com.marzz.maintenance_management_service.repository.MachineRepository;
import com.marzz.maintenance_management_service.repository.UserRepository;
import com.marzz.maintenance_management_service.service.MachineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.machineRepository = machineRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Machine createMachine(MachineDto machineDTO) {
        Machine machine = DtoToEntity(machineDTO);
        User user = userRepository.findById(machineDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + machineDTO.getUserId()));

        machine.setUser(user);
        return machineRepository.save(machine);
    }

    @Override
    public void updateMachine(MachineDto machineDTO) {
        Machine machine = DtoToEntity(machineDTO);
        User user = userRepository.findById(machineDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + machineDTO.getUserId()));

        Machine machineToUpdate = machineRepository.findById(machine.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id :: " + machine.getId()));

        machineToUpdate.setName(machine.getName());
        machineToUpdate.setType(machine.getType());
        machineToUpdate.setAcquisitionDate(machine.getAcquisitionDate());
        machineToUpdate.setStatus(machine.getStatus());
        machineToUpdate.setLastMaintenance(machine.getLastMaintenance());
        machineToUpdate.setUsefulLife(machine.getUsefulLife());
        machineToUpdate.setPhoto(machine.getPhoto());
        machineToUpdate.setUser(user);

        machineRepository.save(machine);
    }

    @Override
    public void deleteMachine(int id) {
        machineRepository.deleteById(id);
    }

    @Override
    public Machine getMachineById(int id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id :: " + id));
    }

    @Override
    public List<Machine> getMachines() {
        return machineRepository.findAll();
    }

    @Override
    public List<Machine> getMachinesByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return machineRepository.getMachinesByUser(user);
    }

    private MachineDto EntityToDto(Machine machine) { return modelMapper.map(machine, MachineDto.class); }

    private Machine DtoToEntity(MachineDto machineDTO) {
        return modelMapper.map(machineDTO, Machine.class);
    }
}
