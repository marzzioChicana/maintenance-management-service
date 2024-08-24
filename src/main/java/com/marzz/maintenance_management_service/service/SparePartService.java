package com.marzz.maintenance_management_service.service;

import com.marzz.maintenance_management_service.model.SparePart;

import java.util.List;

public interface SparePartService {
    public abstract SparePart createSparePart(SparePart sparePart);
    public abstract void updateSparePart(SparePart sparePart);
    public abstract void deleteSparePart(SparePart sparePart);
    public abstract SparePart getSparePartById(int id);
    public abstract List<SparePart> getSpareParts();
}
