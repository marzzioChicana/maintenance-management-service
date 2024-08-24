package com.marzz.maintenance_management_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    private Long cost;

    private String description;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
}
