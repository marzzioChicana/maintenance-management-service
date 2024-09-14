package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.dto.SparePartDto;
import com.marzz.maintenance_management_service.model.SparePart;

import java.util.List;

public interface SparePartService {
    public abstract SparePart createSparePart(SparePartDto sparePartDto);
    public abstract void updateSparePart(int id, SparePartDto sparePartDto);
    public abstract void deleteSparePart(int id);
    public abstract SparePart getSparePartById(int id);
    public abstract List<SparePart> getSpareParts();
    public abstract List<SparePart> getSparePartsByUserId(int userId);
    public abstract boolean isSparePartExist(int id);
}
