package com.marzz.maintenance_management_service.controller;

import com.marzz.maintenance_management_service.dto.SparePartDto;
import com.marzz.maintenance_management_service.exception.ValidationException;
import com.marzz.maintenance_management_service.model.SparePart;
import com.marzz.maintenance_management_service.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping("/api/maintenance/service/v1/spare/parts")
public class SparePartController {
    @Autowired
    private SparePartService sparePartService;

    public SparePartController() { }

    @GetMapping
    public ResponseEntity<List<SparePart>> getAllSpareParts() {
        return new ResponseEntity<>(sparePartService.getSpareParts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePart> getSparePartById(@PathVariable int id) {
        return new ResponseEntity<>(sparePartService.getSparePartById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SparePart>> getSparePartsByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(sparePartService.getSparePartsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SparePart> createSparePart(@RequestBody SparePartDto sparePartDto) {
        validateSparePart(sparePartDto);
        return new ResponseEntity<>(sparePartService.createSparePart(sparePartDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSparePart(@PathVariable int id, @RequestBody SparePartDto sparePartDto) {
        boolean isExist = sparePartService.isSparePartExist(id);

        if(isExist) {
            validateSparePart(sparePartDto);
            sparePartService.updateSparePart(id, sparePartDto);
            return new ResponseEntity<>("Spare part is updated succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Spare part is not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSparePart(@PathVariable int id) {
        sparePartService.deleteSparePart(id);
        return new ResponseEntity<>("Spare part deleted successfully", HttpStatus.OK);
    }

    private void validateSparePart(SparePartDto sparePartDto) {
        if(sparePartDto.getName() == null || sparePartDto.getName().trim().isEmpty()){
            throw new ValidationException("Name must be mandatory");
        }

        if(sparePartDto.getCode() == null || sparePartDto.getCode().trim().isEmpty()){
            throw new ValidationException("Code must be mandatory");
        }

        if(sparePartDto.getQuantity() == null || sparePartDto.getQuantity() < 0){
            throw new ValidationException("Quantity must be mandatory");
        }

        if(sparePartDto.getSupplier() == null || sparePartDto.getSupplier().trim().isEmpty()){
            throw new ValidationException("Supplier must be mandatory");
        }

        if(sparePartDto.getPrice() == null || sparePartDto.getPrice() < 0){
            throw new ValidationException("Cost must be mandatory");
        }
    }
}
