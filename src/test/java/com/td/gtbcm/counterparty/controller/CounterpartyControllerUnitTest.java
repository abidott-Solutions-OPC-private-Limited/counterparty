package com.td.gtbcm.counterparty.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.gtbcm.counterparty.dto.ApiResponse;
import com.td.gtbcm.counterparty.dto.CounterpartyRequest;
import com.td.gtbcm.counterparty.dto.PartyAccount;
import com.td.gtbcm.counterparty.dto.PartyAddress;
import com.td.gtbcm.counterparty.dto.Agent;
import com.td.gtbcm.counterparty.dto.AgentAddress;
import com.td.gtbcm.counterparty.dto.AgentIdentification;
import com.td.gtbcm.counterparty.exception.DuplicateCounterpartyException;
import com.td.gtbcm.counterparty.exception.JsonSchemaValidationException;
import com.td.gtbcm.counterparty.service.CounterpartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CounterpartyControllerUnitTest {

    @Mock
    private CounterpartyService counterpartyService;

    @InjectMocks
    private CounterpartyController counterpartyController;

    private CounterpartyRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = createValidCounterpartyRequest();
    }

    @Test
    void createCounterparty_Success() {
        // Given
        String expectedCounterpartyId = "test-uuid-123";
        when(counterpartyService.createCounterparty(any(CounterpartyRequest.class)))
                .thenReturn(expectedCounterpartyId);

        // When
        ResponseEntity<ApiResponse> response = counterpartyController.createCounterparty(validRequest);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("SUCCESS", response.getBody().getStatus());
        assertEquals("Counterparty for ClientId USC12345 created successfully", response.getBody().getMessage());
        assertEquals(expectedCounterpartyId, response.getBody().getCounterpartyId());
        verify(counterpartyService).createCounterparty(validRequest);
    }



    private CounterpartyRequest createValidCounterpartyRequest() {
        CounterpartyRequest request = new CounterpartyRequest();
        request.setMessageId("MSG123456");
        request.setTraceabilityId("TRACE89765");
        request.setClientId("USC12345");
        request.setAlternatePartyId("usbcCorpID1");
        request.setPartyType("Debtor");
        request.setPartyName("Acme Corporation");
        request.setPartyNickName("Acme");
        request.setPartyOrganizationId("ORG-2025");
        request.setPartyPrivateId("PRIV-777");
        request.setCreatedBy("AB");
        request.setApprovedBy("admin");

        // Party Account
        PartyAccount partyAccount = new PartyAccount();
        partyAccount.setIban("US12ABCD123456789012345678901");
        partyAccount.setOtherId("Accnt-1234");
        partyAccount.setCurrency("USD");
        request.setPartyAccount(partyAccount);

        // Party Address
        PartyAddress partyAddress = new PartyAddress();
        partyAddress.setDepartment("dept1");
        partyAddress.setSubDepartment("subdept1");
        partyAddress.setStreetName("Main Street");
        partyAddress.setBuildingNumber("1234");
        partyAddress.setBuildingName("Acme Towers");
        partyAddress.setFloor("19");
        partyAddress.setTownLocationName("Manhattan");
        partyAddress.setDistrictName("District A");
        partyAddress.setSubdivision("Subdivision 1");
        partyAddress.setCountry("US");
        partyAddress.setEmail("john.doe@tds.com");
        request.setPartyAddress(partyAddress);

        // Agent
        Agent agent = new Agent();
        agent.setAgentName("Clearing Agent A");

        AgentIdentification identification = new AgentIdentification();
        identification.setClearingSystemMemberCode("USABA");
        identification.setClearingSystemMemberId("MEM-1234");
        identification.setBic("NRTHUS33");
        agent.setIdentification(identification);

        AgentAddress agentAddress = new AgentAddress();
        agentAddress.setDepartment("dept1");
        agentAddress.setSubDepartment("subdept1");
        agentAddress.setStreetName("Street X");
        agentAddress.setBuildingNumber("123");
        agentAddress.setBuildingName("Agent Building");
        agentAddress.setFloor("1");
        agentAddress.setPostBox("PO123");
        agentAddress.setRoom("Room 101");
        agentAddress.setPostalCode("12345");
        agentAddress.setTownName("Agent Town");
        agentAddress.setTownLocationName("Agent Location");
        agentAddress.setDistrictName("Agent District");
        agentAddress.setSubdivision("Agent Subdivision");
        agentAddress.setCountry("US");
        agentAddress.setEmail("agent@tds.com");
        agent.setAddress(agentAddress);

        request.setAgent(agent);

        return request;
    }
}
