package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartRepository extends JpaRepository<SparePart, Integer> {
}
