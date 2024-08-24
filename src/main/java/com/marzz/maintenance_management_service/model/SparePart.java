package com.marzz.maintenance_management_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String code;

    private int quantity;

    private String supplier;

    private int usefulLife;

    private Long price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
