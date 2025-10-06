package com.gtbcm.party.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String status;
    private String message;
    private String counterpartyId;
}
