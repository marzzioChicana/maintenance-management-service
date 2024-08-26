package com.marzz.maintenance_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private int id;
    private String username;
    private String token;
}
