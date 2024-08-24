package com.marzz.maintenance_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparePartDTO {
    private String name;
    private String code;
    private int quantity;
    private String supplier;
    private int usefulLife;
    private Long price;
    private int userId;
}
