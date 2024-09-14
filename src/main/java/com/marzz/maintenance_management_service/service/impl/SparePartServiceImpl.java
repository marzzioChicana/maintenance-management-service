package com.marzz.maintenance_management_service.service.impl;

import com.marzz.maintenance_management_service.dto.SparePartDto;
import com.marzz.maintenance_management_service.exception.ResourceNotFoundException;
import com.marzz.maintenance_management_service.model.SparePart;
import com.marzz.maintenance_management_service.model.User;
import com.marzz.maintenance_management_service.repository.SparePartRepository;
import com.marzz.maintenance_management_service.repository.UserRepository;
import com.marzz.maintenance_management_service.service.SparePartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SparePartServiceImpl implements SparePartService {

    private final SparePartRepository sparePartRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SparePartServiceImpl(SparePartRepository sparePartRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.sparePartRepository = sparePartRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SparePart createSparePart(SparePartDto sparePartDto) {
        SparePart sparePart = DtoToEntity(sparePartDto);
        User user = userRepository.findById(sparePartDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + sparePartDto.getUserId()));

        sparePart.setUser(user);
        return sparePartRepository.save(sparePart);
    }

    @Override
    public void updateSparePart(int id, SparePartDto sparePartDto) {
        SparePart sparePart = DtoToEntity(sparePartDto);

        User user = userRepository.findById(sparePartDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + sparePartDto.getUserId()));

        sparePart.setId(id);
        sparePart.setUser(user);
        sparePartRepository.save(sparePart);
    }

    @Override
    public void deleteSparePart(int id) {
        sparePartRepository.deleteById(id);
    }

    @Override
    public SparePart getSparePartById(int id) {
        return sparePartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SparePart not found for this id :: " + id));
    }

    @Override
    public List<SparePart> getSpareParts() {
        return sparePartRepository.findAll();
    }

    @Override
    public List<SparePart> getSparePartsByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        return sparePartRepository.getSparePartByUser(user);
    }

    @Override
    public boolean isSparePartExist(int id) {
        return sparePartRepository.existsById(id);
    }

    private SparePartDto EntityToDto(SparePart sparePart) { return modelMapper.map(sparePart, SparePartDto.class); }

    private SparePart DtoToEntity(SparePartDto sparePartDto) {
        return modelMapper.map(sparePartDto, SparePart.class);
    }
}
