package com.td.gtbcm.counterparty.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CounterpartyEntityTest {

    @Test
    void builder_AllFields_Success() {
        // Given
        String counterpartyId = UUID.randomUUID().toString();
        String clientId = "USC12345";
        String partyName = "Acme Corporation";
        LocalDateTime now = LocalDateTime.now();

        // When
        CounterpartyEntity entity = CounterpartyEntity.builder()
                .cpSeqId(1L)
                .counterpartyId(counterpartyId)
                .clientId(clientId)
                .alternatePartyId("ALT123")
                .partyType("Debtor")
                .partyName(partyName)
                .partyNickName("Acme")
                .partyOrgId("ORG123")
                .partyPrivateId("PRIV123")
                .partyAccountIban("IBAN123")
                .partyAccountOtherId("OTHER123")
                .partyAccountCurrency("USD")
                .partyAddrDepartment("dept")
                .partyAddrSubDepartment("subdept")
                .partyAddrStreetName("Main Street")
                .partyAddrBuildingNo("123")
                .partyAddrBuildingName("Building")
                .partyAddrFloor("1")
                .partyAddrTownLocationName("Town")
                .partyAddrDistrictName("District")
                .partyAddrSubdivision("Subdivision")
                .partyAddrCountry("US")
                .partyPhoneNumber("1234567890")
                .partyEmail("test@example.com")
                .agentName("Agent Name")
                .agentClearingSysMemberCode("CODE")
                .agentBic("BIC123")
                .agentClearingSysMemberId("ID123")
                .agentAddrDepartment("Agent Dept")
                .agentAddrSubDepartment("Agent SubDept")
                .agentAddrStreetName("Agent Street")
                .agentAddrBuildingNo("Agent 123")
                .agentAddrBuildingName("Agent Building")
                .agentAddrFloor("Agent Floor")
                .agentAddrPostBox("Agent PO")
                .agentAddrRoom("Agent Room")
                .agentAddrPostalCode("Agent Postal")
                .agentAddrTownName("Agent Town")
                .agentAddrTownLocationName("Agent Location")
                .agentAddrDistrictName("Agent District")
                .agentAddrSubdivision("Agent Subdivision")
                .agentAddrCountry("US")
                .agentPhoneNumber("9876543210")
                .agentEmail("agent@example.com")
                .createdBy("AB")
                .approvedBy("admin")
                .lastModifiedBy("CD")
                .createdDatetime(now)
                .lastModifiedDatetime(now.plusHours(1))
                .jsonRequest("{}")
                .enabled(true)
                .build();

        // Then
        assertNotNull(entity);
        assertEquals(1L, entity.getCpSeqId());
        assertEquals(counterpartyId, entity.getCounterpartyId());
        assertEquals(clientId, entity.getClientId());
        assertEquals("ALT123", entity.getAlternatePartyId());
        assertEquals("Debtor", entity.getPartyType());
        assertEquals(partyName, entity.getPartyName());
        assertEquals("Acme", entity.getPartyNickName());
        assertEquals("ORG123", entity.getPartyOrgId());
        assertEquals("PRIV123", entity.getPartyPrivateId());
        assertEquals("IBAN123", entity.getPartyAccountIban());
        assertEquals("OTHER123", entity.getPartyAccountOtherId());
        assertEquals("USD", entity.getPartyAccountCurrency());
        assertEquals("dept", entity.getPartyAddrDepartment());
        assertEquals("subdept", entity.getPartyAddrSubDepartment());
        assertEquals("Main Street", entity.getPartyAddrStreetName());
        assertEquals("123", entity.getPartyAddrBuildingNo());
        assertEquals("Building", entity.getPartyAddrBuildingName());
        assertEquals("1", entity.getPartyAddrFloor());
        assertEquals("Town", entity.getPartyAddrTownLocationName());
        assertEquals("District", entity.getPartyAddrDistrictName());
        assertEquals("Subdivision", entity.getPartyAddrSubdivision());
        assertEquals("US", entity.getPartyAddrCountry());
        assertEquals("1234567890", entity.getPartyPhoneNumber());
        assertEquals("test@example.com", entity.getPartyEmail());
        assertEquals("Agent Name", entity.getAgentName());
        assertEquals("CODE", entity.getAgentClearingSysMemberCode());
        assertEquals("BIC123", entity.getAgentBic());
        assertEquals("ID123", entity.getAgentClearingSysMemberId());
        assertEquals("Agent Dept", entity.getAgentAddrDepartment());
        assertEquals("Agent SubDept", entity.getAgentAddrSubDepartment());
        assertEquals("Agent Street", entity.getAgentAddrStreetName());
        assertEquals("Agent 123", entity.getAgentAddrBuildingNo());
        assertEquals("Agent Building", entity.getAgentAddrBuildingName());
        assertEquals("Agent Floor", entity.getAgentAddrFloor());
        assertEquals("Agent PO", entity.getAgentAddrPostBox());
        assertEquals("Agent Room", entity.getAgentAddrRoom());
        assertEquals("Agent Postal", entity.getAgentAddrPostalCode());
        assertEquals("Agent Town", entity.getAgentAddrTownName());
        assertEquals("Agent Location", entity.getAgentAddrTownLocationName());
        assertEquals("Agent District", entity.getAgentAddrDistrictName());
        assertEquals("Agent Subdivision", entity.getAgentAddrSubdivision());
        assertEquals("US", entity.getAgentAddrCountry());
        assertEquals("9876543210", entity.getAgentPhoneNumber());
        assertEquals("agent@example.com", entity.getAgentEmail());
        assertEquals("AB", entity.getCreatedBy());
        assertEquals("admin", entity.getApprovedBy());
        assertEquals("CD", entity.getLastModifiedBy());
        assertEquals(now, entity.getCreatedDatetime());
        assertEquals(now.plusHours(1), entity.getLastModifiedDatetime());
        assertEquals("{}", entity.getJsonRequest());
        assertTrue(entity.getEnabled());
    }

    @Test
    void builder_MinimalFields_Success() {
        // Given
        String counterpartyId = UUID.randomUUID().toString();
        String clientId = "USC12345";
        String partyName = "Acme Corporation";
        LocalDateTime now = LocalDateTime.now();

        // When
        CounterpartyEntity entity = CounterpartyEntity.builder()
                .counterpartyId(counterpartyId)
                .clientId(clientId)
                .partyType("Debtor")
                .partyName(partyName)
                .partyAddrStreetName("Main Street")
                .partyAddrBuildingNo("123")
                .partyAddrSubdivision("Subdivision")
                .partyAddrCountry("US")
                .agentName("Agent Name")
                .agentAddrSubdivision("Agent Subdivision")
                .agentAddrCountry("US")
                .createdBy("AB")
                .approvedBy("admin")
                .createdDatetime(now)
                .jsonRequest("{}")
                .enabled(true)
                .build();

        // Then
        assertNotNull(entity);
        assertEquals(counterpartyId, entity.getCounterpartyId());
        assertEquals(clientId, entity.getClientId());
        assertEquals("Debtor", entity.getPartyType());
        assertEquals(partyName, entity.getPartyName());
        assertEquals("Main Street", entity.getPartyAddrStreetName());
        assertEquals("123", entity.getPartyAddrBuildingNo());
        assertEquals("Subdivision", entity.getPartyAddrSubdivision());
        assertEquals("US", entity.getPartyAddrCountry());
        assertEquals("Agent Name", entity.getAgentName());
        assertEquals("Agent Subdivision", entity.getAgentAddrSubdivision());
        assertEquals("US", entity.getAgentAddrCountry());
        assertEquals("AB", entity.getCreatedBy());
        assertEquals("admin", entity.getApprovedBy());
        assertEquals(now, entity.getCreatedDatetime());
        assertEquals("{}", entity.getJsonRequest());
        assertTrue(entity.getEnabled());
    }

    @Test
    void noArgsConstructor_Success() {
        // When
        CounterpartyEntity entity = new CounterpartyEntity();

        // Then
        assertNotNull(entity);
    }

    @Test
    void allArgsConstructor_Success() {
        // Given
        String counterpartyId = UUID.randomUUID().toString();
        String clientId = "USC12345";
        String partyName = "Acme Corporation";
        LocalDateTime now = LocalDateTime.now();

        // When
        CounterpartyEntity entity = new CounterpartyEntity(
                1L, counterpartyId, clientId, "ALT123", "Debtor", partyName, "Acme",
                "ORG123", "PRIV123", "IBAN123", "OTHER123", "USD", "dept", "subdept",
                "Main Street", "123", "Building", "1", "Town", "District", "Subdivision",
                "US", "1234567890", "test@example.com", "Agent Name", "CODE", "BIC123",
                "ID123", "Agent Dept", "Agent SubDept", "Agent Street", "Agent 123",
                "Agent Building", "Agent Floor", "Agent PO", "Agent Room", "Agent Postal",
                "Agent Town", "Agent Location", "Agent District", "Agent Subdivision",
                "US", "9876543210", "agent@example.com", "AB", "admin", "CD", now,
                now.plusHours(1), "{}", true
        );

        // Then
        assertNotNull(entity);
        assertEquals(1L, entity.getCpSeqId());
        assertEquals(counterpartyId, entity.getCounterpartyId());
        assertEquals(clientId, entity.getClientId());
        assertEquals("ALT123", entity.getAlternatePartyId());
        assertEquals("Debtor", entity.getPartyType());
        assertEquals(partyName, entity.getPartyName());
        assertEquals("Acme", entity.getPartyNickName());
        assertEquals("ORG123", entity.getPartyOrgId());
        assertEquals("PRIV123", entity.getPartyPrivateId());
        assertEquals("IBAN123", entity.getPartyAccountIban());
        assertEquals("OTHER123", entity.getPartyAccountOtherId());
        assertEquals("USD", entity.getPartyAccountCurrency());
        assertEquals("dept", entity.getPartyAddrDepartment());
        assertEquals("subdept", entity.getPartyAddrSubDepartment());
        assertEquals("Main Street", entity.getPartyAddrStreetName());
        assertEquals("123", entity.getPartyAddrBuildingNo());
        assertEquals("Building", entity.getPartyAddrBuildingName());
        assertEquals("1", entity.getPartyAddrFloor());
        assertEquals("Town", entity.getPartyAddrTownLocationName());
        assertEquals("District", entity.getPartyAddrDistrictName());
        assertEquals("Subdivision", entity.getPartyAddrSubdivision());
        assertEquals("US", entity.getPartyAddrCountry());
        assertEquals("1234567890", entity.getPartyPhoneNumber());
        assertEquals("test@example.com", entity.getPartyEmail());
        assertEquals("Agent Name", entity.getAgentName());
        assertEquals("CODE", entity.getAgentClearingSysMemberCode());
        assertEquals("BIC123", entity.getAgentBic());
        assertEquals("ID123", entity.getAgentClearingSysMemberId());
        assertEquals("Agent Dept", entity.getAgentAddrDepartment());
        assertEquals("Agent SubDept", entity.getAgentAddrSubDepartment());
        assertEquals("Agent Street", entity.getAgentAddrStreetName());
        assertEquals("Agent 123", entity.getAgentAddrBuildingNo());
        assertEquals("Agent Building", entity.getAgentAddrBuildingName());
        assertEquals("Agent Floor", entity.getAgentAddrFloor());
        assertEquals("Agent PO", entity.getAgentAddrPostBox());
        assertEquals("Agent Room", entity.getAgentAddrRoom());
        assertEquals("Agent Postal", entity.getAgentAddrPostalCode());
        assertEquals("Agent Town", entity.getAgentAddrTownName());
        assertEquals("Agent Location", entity.getAgentAddrTownLocationName());
        assertEquals("Agent District", entity.getAgentAddrDistrictName());
        assertEquals("Agent Subdivision", entity.getAgentAddrSubdivision());
        assertEquals("US", entity.getAgentAddrCountry());
        assertEquals("9876543210", entity.getAgentPhoneNumber());
        assertEquals("agent@example.com", entity.getAgentEmail());
        assertEquals("AB", entity.getCreatedBy());
        assertEquals("admin", entity.getApprovedBy());
        assertEquals("CD", entity.getLastModifiedBy());
        assertEquals(now, entity.getCreatedDatetime());
        assertEquals(now.plusHours(1), entity.getLastModifiedDatetime());
        assertEquals("{}", entity.getJsonRequest());
        assertTrue(entity.getEnabled());
    }

    @Test
    void settersAndGetters_Success() {
        // Given
        CounterpartyEntity entity = new CounterpartyEntity();
        String counterpartyId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        // When
        entity.setCpSeqId(1L);
        entity.setCounterpartyId(counterpartyId);
        entity.setClientId("USC12345");
        entity.setAlternatePartyId("ALT123");
        entity.setPartyType("Debtor");
        entity.setPartyName("Acme Corporation");
        entity.setPartyNickName("Acme");
        entity.setPartyOrgId("ORG123");
        entity.setPartyPrivateId("PRIV123");
        entity.setPartyAccountIban("IBAN123");
        entity.setPartyAccountOtherId("OTHER123");
        entity.setPartyAccountCurrency("USD");
        entity.setPartyAddrDepartment("dept");
        entity.setPartyAddrSubDepartment("subdept");
        entity.setPartyAddrStreetName("Main Street");
        entity.setPartyAddrBuildingNo("123");
        entity.setPartyAddrBuildingName("Building");
        entity.setPartyAddrFloor("1");
        entity.setPartyAddrTownLocationName("Town");
        entity.setPartyAddrDistrictName("District");
        entity.setPartyAddrSubdivision("Subdivision");
        entity.setPartyAddrCountry("US");
        entity.setPartyPhoneNumber("1234567890");
        entity.setPartyEmail("test@example.com");
        entity.setAgentName("Agent Name");
        entity.setAgentClearingSysMemberCode("CODE");
        entity.setAgentBic("BIC123");
        entity.setAgentClearingSysMemberId("ID123");
        entity.setAgentAddrDepartment("Agent Dept");
        entity.setAgentAddrSubDepartment("Agent SubDept");
        entity.setAgentAddrStreetName("Agent Street");
        entity.setAgentAddrBuildingNo("Agent 123");
        entity.setAgentAddrBuildingName("Agent Building");
        entity.setAgentAddrFloor("Agent Floor");
        entity.setAgentAddrPostBox("Agent PO");
        entity.setAgentAddrRoom("Agent Room");
        entity.setAgentAddrPostalCode("Agent Postal");
        entity.setAgentAddrTownName("Agent Town");
        entity.setAgentAddrTownLocationName("Agent Location");
        entity.setAgentAddrDistrictName("Agent District");
        entity.setAgentAddrSubdivision("Agent Subdivision");
        entity.setAgentAddrCountry("US");
        entity.setAgentPhoneNumber("9876543210");
        entity.setAgentEmail("agent@example.com");
        entity.setCreatedBy("AB");
        entity.setApprovedBy("admin");
        entity.setLastModifiedBy("CD");
        entity.setCreatedDatetime(now);
        entity.setLastModifiedDatetime(now.plusHours(1));
        entity.setJsonRequest("{}");
        entity.setEnabled(true);

        // Then
        assertEquals(1L, entity.getCpSeqId());
        assertEquals(counterpartyId, entity.getCounterpartyId());
        assertEquals("USC12345", entity.getClientId());
        assertEquals("ALT123", entity.getAlternatePartyId());
        assertEquals("Debtor", entity.getPartyType());
        assertEquals("Acme Corporation", entity.getPartyName());
        assertEquals("Acme", entity.getPartyNickName());
        assertEquals("ORG123", entity.getPartyOrgId());
        assertEquals("PRIV123", entity.getPartyPrivateId());
        assertEquals("IBAN123", entity.getPartyAccountIban());
        assertEquals("OTHER123", entity.getPartyAccountOtherId());
        assertEquals("USD", entity.getPartyAccountCurrency());
        assertEquals("dept", entity.getPartyAddrDepartment());
        assertEquals("subdept", entity.getPartyAddrSubDepartment());
        assertEquals("Main Street", entity.getPartyAddrStreetName());
        assertEquals("123", entity.getPartyAddrBuildingNo());
        assertEquals("Building", entity.getPartyAddrBuildingName());
        assertEquals("1", entity.getPartyAddrFloor());
        assertEquals("Town", entity.getPartyAddrTownLocationName());
        assertEquals("District", entity.getPartyAddrDistrictName());
        assertEquals("Subdivision", entity.getPartyAddrSubdivision());
        assertEquals("US", entity.getPartyAddrCountry());
        assertEquals("1234567890", entity.getPartyPhoneNumber());
        assertEquals("test@example.com", entity.getPartyEmail());
        assertEquals("Agent Name", entity.getAgentName());
        assertEquals("CODE", entity.getAgentClearingSysMemberCode());
        assertEquals("BIC123", entity.getAgentBic());
        assertEquals("ID123", entity.getAgentClearingSysMemberId());
        assertEquals("Agent Dept", entity.getAgentAddrDepartment());
        assertEquals("Agent SubDept", entity.getAgentAddrSubDepartment());
        assertEquals("Agent Street", entity.getAgentAddrStreetName());
        assertEquals("Agent 123", entity.getAgentAddrBuildingNo());
        assertEquals("Agent Building", entity.getAgentAddrBuildingName());
        assertEquals("Agent Floor", entity.getAgentAddrFloor());
        assertEquals("Agent PO", entity.getAgentAddrPostBox());
        assertEquals("Agent Room", entity.getAgentAddrRoom());
        assertEquals("Agent Postal", entity.getAgentAddrPostalCode());
        assertEquals("Agent Town", entity.getAgentAddrTownName());
        assertEquals("Agent Location", entity.getAgentAddrTownLocationName());
        assertEquals("Agent District", entity.getAgentAddrDistrictName());
        assertEquals("Agent Subdivision", entity.getAgentAddrSubdivision());
        assertEquals("US", entity.getAgentAddrCountry());
        assertEquals("9876543210", entity.getAgentPhoneNumber());
        assertEquals("agent@example.com", entity.getAgentEmail());
        assertEquals("AB", entity.getCreatedBy());
        assertEquals("admin", entity.getApprovedBy());
        assertEquals("CD", entity.getLastModifiedBy());
        assertEquals(now, entity.getCreatedDatetime());
        assertEquals(now.plusHours(1), entity.getLastModifiedDatetime());
        assertEquals("{}", entity.getJsonRequest());
        assertTrue(entity.getEnabled());
    }
}
