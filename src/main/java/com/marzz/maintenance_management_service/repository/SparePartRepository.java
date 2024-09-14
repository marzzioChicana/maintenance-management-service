package com.marzz.maintenance_management_service.repository;

import com.marzz.maintenance_management_service.model.SparePart;
import com.marzz.maintenance_management_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SparePartRepository extends JpaRepository<SparePart, Integer> {
    List<SparePart> getSparePartByUser(User user);
}
