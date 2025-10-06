package com.td.gtbcm.counterparty.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.gtbcm.counterparty.dto.CounterpartyRequest;
import com.td.gtbcm.counterparty.dto.PartyAccount;
import com.td.gtbcm.counterparty.dto.PartyAddress;
import com.td.gtbcm.counterparty.dto.Agent;
import com.td.gtbcm.counterparty.dto.AgentAddress;
import com.td.gtbcm.counterparty.dto.AgentIdentification;
import com.td.gtbcm.counterparty.service.CounterpartyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CounterpartyController.class)
class CounterpartyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CounterpartyService counterpartyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCounterparty_Success() throws Exception {
        // Given
        String counterpartyId = UUID.randomUUID().toString();
        when(counterpartyService.createCounterparty(any(CounterpartyRequest.class)))
            .thenReturn(counterpartyId);

        CounterpartyRequest request = createValidCounterpartyRequest();

        // When & Then
        mockMvc.perform(post("/api/v1/counterparty/create-counterparty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.message").value("Counterparty for ClientId USC12345 created successfully"))
                .andExpect(jsonPath("$.counterpartyId").value(counterpartyId));
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
        agentAddress.setStreetName("Street X");
        agentAddress.setCountry("US");
        agentAddress.setEmail("agent@tds.com");
        agent.setAddress(agentAddress);

        request.setAgent(agent);

        return request;
    }
}
