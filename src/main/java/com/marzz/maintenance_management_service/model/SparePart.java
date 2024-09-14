package com.marzz.maintenance_management_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String code;

    private Integer quantity;

    private String supplier;

    private Long price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
