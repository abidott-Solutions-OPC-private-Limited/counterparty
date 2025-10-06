package com.td.gtbcm.counterparty.controller;

import com.td.gtbcm.counterparty.dto.ApiResponse;
import com.td.gtbcm.counterparty.dto.CounterpartyRequest;
import com.td.gtbcm.counterparty.service.CounterpartyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/counterparty")
@RequiredArgsConstructor
@Slf4j
public class CounterpartyController {

    private final CounterpartyService counterpartyService;

    @PostMapping("/create-counterparty")
    public ResponseEntity<ApiResponse> createCounterparty(@RequestBody CounterpartyRequest request) {
        log.info("Received request to create counterparty for clientId: {}", request.getClientId());
        
        try {
            String counterpartyId = counterpartyService.createCounterparty(request);
            
            ApiResponse response = new ApiResponse(
                "SUCCESS",
                String.format("Counterparty for ClientId %s created successfully", request.getClientId()),
                counterpartyId
            );
            
            log.info("Counterparty created successfully with ID: {}", counterpartyId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            log.error("Error creating counterparty for clientId: {}", request.getClientId(), e);
            throw e; // Let GlobalExceptionHandler handle it
        }
    }
}
