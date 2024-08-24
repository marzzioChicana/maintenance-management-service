package com.marzz.maintenance_management_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    private Date acquisitionDate;

    private String status;

    private Date lastMaintenance;

    private int usefulLife;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
