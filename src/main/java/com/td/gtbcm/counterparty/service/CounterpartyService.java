package com.td.gtbcm.counterparty.service;

import com.td.gtbcm.counterparty.dto.CounterpartyRequest;

public interface CounterpartyService {
    
    /**
     * Creates a new counterparty based on the provided request
     * 
     * @param request The counterparty creation request
     * @return The generated counterparty ID
     * @throws IllegalArgumentException if required fields are missing
     * @throws DuplicateCounterpartyException if a duplicate counterparty exists
     */
    String createCounterparty(CounterpartyRequest request);
}