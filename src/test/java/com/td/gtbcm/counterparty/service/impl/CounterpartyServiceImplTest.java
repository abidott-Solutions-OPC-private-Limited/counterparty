package com.td.gtbcm.counterparty.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.gtbcm.counterparty.dto.CounterpartyRequest;
import com.td.gtbcm.counterparty.dto.PartyAccount;
import com.td.gtbcm.counterparty.dto.PartyAddress;
import com.td.gtbcm.counterparty.dto.Agent;
import com.td.gtbcm.counterparty.dto.AgentAddress;
import com.td.gtbcm.counterparty.dto.AgentIdentification;
import com.td.gtbcm.counterparty.entity.CounterpartyEntity;
import com.td.gtbcm.counterparty.exception.DuplicateCounterpartyException;
import com.td.gtbcm.counterparty.exception.JsonSchemaValidationException;
import com.td.gtbcm.counterparty.repository.CounterpartyRepository;
import com.td.gtbcm.counterparty.service.JsonSchemaValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CounterpartyServiceImplTest {

    @Mock
    private CounterpartyRepository counterpartyRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private JsonSchemaValidatorService jsonSchemaValidatorService;

    @InjectMocks
    private CounterpartyServiceImpl counterpartyService;

    private CounterpartyRequest validRequest;
    private CounterpartyEntity savedEntity;

    @BeforeEach
    void setUp() {
        validRequest = createValidCounterpartyRequest();
        savedEntity = createCounterpartyEntity();
    }

    @Test
    void createCounterparty_Success() throws Exception {
        // Given
        JsonNode mockJsonNode = mock(JsonNode.class);
        when(objectMapper.valueToTree(validRequest)).thenReturn(mockJsonNode);
        doNothing().when(jsonSchemaValidatorService).schemaValidation(mockJsonNode);
        when(counterpartyRepository.findActiveDuplicate(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Optional.empty());
        when(counterpartyRepository.save(any(CounterpartyEntity.class))).thenReturn(savedEntity);

        // When
        String result = counterpartyService.createCounterparty(validRequest);

        // Then
        assertNotNull(result);
        assertEquals(savedEntity.getCounterpartyId(), result);
        verify(jsonSchemaValidatorService).schemaValidation(mockJsonNode);
        verify(counterpartyRepository).findActiveDuplicate(anyString(), anyString(), anyString(), anyString());
        verify(counterpartyRepository).save(any(CounterpartyEntity.class));
    }

    @Test
    void createCounterparty_SchemaValidationFails() throws Exception {
        // Given
        JsonNode mockJsonNode = mock(JsonNode.class);
        when(objectMapper.valueToTree(validRequest)).thenReturn(mockJsonNode);
        doThrow(new JsonSchemaValidationException("Validation failed"))
                .when(jsonSchemaValidatorService).schemaValidation(mockJsonNode);

        // When & Then
        assertThrows(JsonSchemaValidationException.class, () -> {
            counterpartyService.createCounterparty(validRequest);
        });

        verify(jsonSchemaValidatorService).schemaValidation(mockJsonNode);
        verify(counterpartyRepository, never()).findActiveDuplicate(anyString(), anyString(), anyString(), anyString());
        verify(counterpartyRepository, never()).save(any(CounterpartyEntity.class));
    }

    @Test
    void createCounterparty_DuplicateFound() throws Exception {
        // Given
        JsonNode mockJsonNode = mock(JsonNode.class);
        when(objectMapper.valueToTree(validRequest)).thenReturn(mockJsonNode);
        doNothing().when(jsonSchemaValidatorService).schemaValidation(mockJsonNode);
        when(counterpartyRepository.findActiveDuplicate(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(savedEntity));

        // When & Then
        assertThrows(DuplicateCounterpartyException.class, () -> {
            counterpartyService.createCounterparty(validRequest);
        });

        verify(jsonSchemaValidatorService).schemaValidation(mockJsonNode);
        verify(counterpartyRepository).findActiveDuplicate(anyString(), anyString(), anyString(), anyString());
        verify(counterpartyRepository, never()).save(any(CounterpartyEntity.class));
    }

    @Test
    void createCounterparty_WithIbanOnly() throws Exception {
        // Given
        validRequest.getPartyAccount().setOtherId(null);
        JsonNode mockJsonNode = mock(JsonNode.class);
        when(objectMapper.valueToTree(validRequest)).thenReturn(mockJsonNode);
        doNothing().when(jsonSchemaValidatorService).schemaValidation(mockJsonNode);
        when(counterpartyRepository.findActiveDuplicate(anyString(), anyString(), anyString(), isNull()))
                .thenReturn(Optional.empty());
        when(counterpartyRepository.save(any(CounterpartyEntity.class))).thenReturn(savedEntity);

        // When
        String result = counterpartyService.createCounterparty(validRequest);

        // Then
        assertNotNull(result);
        verify(counterpartyRepository).findActiveDuplicate(anyString(), anyString(), anyString(), isNull());
    }

    @Test
    void createCounterparty_WithOtherIdOnly() throws Exception {
        // Given
        validRequest.getPartyAccount().setIban(null);
        JsonNode mockJsonNode = mock(JsonNode.class);
        when(objectMapper.valueToTree(validRequest)).thenReturn(mockJsonNode);
        doNothing().when(jsonSchemaValidatorService).schemaValidation(mockJsonNode);
        when(counterpartyRepository.findActiveDuplicate(anyString(), anyString(), isNull(), anyString()))
                .thenReturn(Optional.empty());
        when(counterpartyRepository.save(any(CounterpartyEntity.class))).thenReturn(savedEntity);

        // When
        String result = counterpartyService.createCounterparty(validRequest);

        // Then
        assertNotNull(result);
        verify(counterpartyRepository).findActiveDuplicate(anyString(), anyString(), isNull(), anyString());
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

    private CounterpartyEntity createCounterpartyEntity() {
        return CounterpartyEntity.builder()
                .cpSeqId(1L)
                .counterpartyId(UUID.randomUUID().toString())
                .clientId("USC12345")
                .alternatePartyId("usbcCorpID1")
                .partyType("Debtor")
                .partyName("Acme Corporation")
                .partyNickName("Acme")
                .partyOrgId("ORG-2025")
                .partyPrivateId("PRIV-777")
                .partyAccountIban("US12ABCD123456789012345678901")
                .partyAccountOtherId("Accnt-1234")
                .partyAccountCurrency("USD")
                .partyAddrDepartment("dept1")
                .partyAddrSubDepartment("subdept1")
                .partyAddrStreetName("Main Street")
                .partyAddrBuildingNo("1234")
                .partyAddrBuildingName("Acme Towers")
                .partyAddrFloor("19")
                .partyAddrTownLocationName("Manhattan")
                .partyAddrDistrictName("District A")
                .partyAddrSubdivision("Subdivision 1")
                .partyAddrCountry("US")
                .partyEmail("john.doe@tds.com")
                .agentName("Clearing Agent A")
                .agentClearingSysMemberCode("USABA")
                .agentBic("NRTHUS33")
                .agentClearingSysMemberId("MEM-1234")
                .agentAddrDepartment("dept1")
                .agentAddrSubDepartment("subdept1")
                .agentAddrStreetName("Street X")
                .agentAddrBuildingNo("123")
                .agentAddrBuildingName("Agent Building")
                .agentAddrFloor("1")
                .agentAddrPostBox("PO123")
                .agentAddrRoom("Room 101")
                .agentAddrPostalCode("12345")
                .agentAddrTownName("Agent Town")
                .agentAddrTownLocationName("Agent Location")
                .agentAddrDistrictName("Agent District")
                .agentAddrSubdivision("Agent Subdivision")
                .agentAddrCountry("US")
                .agentEmail("agent@tds.com")
                .createdBy("AB")
                .approvedBy("admin")
                .createdDatetime(LocalDateTime.now())
                .jsonRequest("{}")
                .enabled(true)
                .build();
    }
}
