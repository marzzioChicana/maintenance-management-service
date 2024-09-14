package com.marzz.maintenance_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparePartDto {
    private String name;
    private String code;
    private Integer quantity;
    private String supplier;
    private Long price;
    private Integer userId;
}
