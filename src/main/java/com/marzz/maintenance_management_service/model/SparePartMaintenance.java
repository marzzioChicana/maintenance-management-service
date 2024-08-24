package com.marzz.maintenance_management_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SparePartMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    @ManyToOne
    @JoinColumn(name = "spare_part_id")
    private SparePart sparePart;

    private int quantity;
}
