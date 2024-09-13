package com.marzz.maintenance_management_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String type;

    private LocalDate acquisitionDate;

    private String status;

    private LocalDate lastMaintenance;

    private Integer usefulLife;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
