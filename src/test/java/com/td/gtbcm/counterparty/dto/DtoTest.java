package com.td.gtbcm.counterparty.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DtoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void counterpartyRequest_AllArgsConstructor() {
        // Given
        String messageId = "MSG123";
        String traceabilityId = "TRACE456";
        String clientId = "USC12345";
        String alternatePartyId = "ALT123";
        String partyType = "Debtor";
        String partyName = "Acme Corp";
        String partyNickName = "Acme";
        String partyOrganizationId = "ORG123";
        String partyPrivateId = "PRIV123";
        PartyAccount partyAccount = new PartyAccount("IBAN123", "OTHER123", "USD");
        PartyAddress partyAddress = new PartyAddress("dept", "subdept", "street", "123", "building", "floor", "town", "district", "subdivision", "US", "email");
        Agent agent = new Agent("Agent Name", null, null);
        String createdBy = "AB";
        String approvedBy = "admin";

        // When
        CounterpartyRequest request = new CounterpartyRequest(
                messageId, traceabilityId, clientId, alternatePartyId, partyType, partyName,
                partyNickName, partyOrganizationId, partyPrivateId, partyAccount, partyAddress,
                agent, createdBy, approvedBy
        );

        // Then
        assertEquals(messageId, request.getMessageId());
        assertEquals(traceabilityId, request.getTraceabilityId());
        assertEquals(clientId, request.getClientId());
        assertEquals(alternatePartyId, request.getAlternatePartyId());
        assertEquals(partyType, request.getPartyType());
        assertEquals(partyName, request.getPartyName());
        assertEquals(partyNickName, request.getPartyNickName());
        assertEquals(partyOrganizationId, request.getPartyOrganizationId());
        assertEquals(partyPrivateId, request.getPartyPrivateId());
        assertEquals(partyAccount, request.getPartyAccount());
        assertEquals(partyAddress, request.getPartyAddress());
        assertEquals(agent, request.getAgent());
        assertEquals(createdBy, request.getCreatedBy());
        assertEquals(approvedBy, request.getApprovedBy());
    }

    @Test
    void counterpartyRequest_NoArgsConstructor() {
        // When
        CounterpartyRequest request = new CounterpartyRequest();

        // Then
        assertNotNull(request);
    }

    @Test
    void counterpartyRequest_JsonSerialization() throws Exception {
        // Given
        CounterpartyRequest request = new CounterpartyRequest();
        request.setClientId("USC12345");
        request.setPartyType("Debtor");
        request.setPartyName("Acme Corp");

        // When
        String json = objectMapper.writeValueAsString(request);

        // Then
        assertNotNull(json);
        assertTrue(json.contains("USC12345"));
        assertTrue(json.contains("Debtor"));
        assertTrue(json.contains("Acme Corp"));
    }

    @Test
    void counterpartyRequest_JsonDeserialization() throws Exception {
        // Given
        String json = """
            {
                "clientId": "USC12345",
                "partyType": "Debtor",
                "partyName": "Acme Corp",
                "partyAccount": {
                    "iban": "IBAN123",
                    "otherId": "OTHER123",
                    "currency": "USD"
                }
            }
            """;

        // When
        CounterpartyRequest request = objectMapper.readValue(json, CounterpartyRequest.class);

        // Then
        assertEquals("USC12345", request.getClientId());
        assertEquals("Debtor", request.getPartyType());
        assertEquals("Acme Corp", request.getPartyName());
        assertNotNull(request.getPartyAccount());
        assertEquals("IBAN123", request.getPartyAccount().getIban());
        assertEquals("OTHER123", request.getPartyAccount().getOtherId());
        assertEquals("USD", request.getPartyAccount().getCurrency());
    }

    @Test
    void partyAccount_AllArgsConstructor() {
        // Given
        String iban = "IBAN123";
        String otherId = "OTHER123";
        String currency = "USD";

        // When
        PartyAccount partyAccount = new PartyAccount(iban, otherId, currency);

        // Then
        assertEquals(iban, partyAccount.getIban());
        assertEquals(otherId, partyAccount.getOtherId());
        assertEquals(currency, partyAccount.getCurrency());
    }

    @Test
    void partyAccount_NoArgsConstructor() {
        // When
        PartyAccount partyAccount = new PartyAccount();

        // Then
        assertNotNull(partyAccount);
    }

    @Test
    void partyAddress_AllArgsConstructor() {
        // Given
        String department = "dept";
        String subDepartment = "subdept";
        String streetName = "street";
        String buildingNumber = "123";
        String buildingName = "building";
        String floor = "floor";
        String townLocationName = "town";
        String districtName = "district";
        String subdivision = "subdivision";
        String country = "US";
        String email = "email@test.com";

        // When
        PartyAddress partyAddress = new PartyAddress(
                department, subDepartment, streetName, buildingNumber, buildingName,
                floor, townLocationName, districtName, subdivision, country, email
        );

        // Then
        assertEquals(department, partyAddress.getDepartment());
        assertEquals(subDepartment, partyAddress.getSubDepartment());
        assertEquals(streetName, partyAddress.getStreetName());
        assertEquals(buildingNumber, partyAddress.getBuildingNumber());
        assertEquals(buildingName, partyAddress.getBuildingName());
        assertEquals(floor, partyAddress.getFloor());
        assertEquals(townLocationName, partyAddress.getTownLocationName());
        assertEquals(districtName, partyAddress.getDistrictName());
        assertEquals(subdivision, partyAddress.getSubdivision());
        assertEquals(country, partyAddress.getCountry());
        assertEquals(email, partyAddress.getEmail());
    }

    @Test
    void agent_AllArgsConstructor() {
        // Given
        String agentName = "Agent Name";
        AgentIdentification identification = new AgentIdentification("code", "id", "bic");
        AgentAddress address = new AgentAddress("dept", "subdept", "street", "123", "building", "floor", "postbox", "room", "postal", "town", "location", "district", "subdivision", "US", "email");

        // When
        Agent agent = new Agent(agentName, identification, address);

        // Then
        assertEquals(agentName, agent.getAgentName());
        assertEquals(identification, agent.getIdentification());
        assertEquals(address, agent.getAddress());
    }

    @Test
    void agentIdentification_AllArgsConstructor() {
        // Given
        String clearingSystemMemberCode = "code";
        String clearingSystemMemberId = "id";
        String bic = "bic";

        // When
        AgentIdentification identification = new AgentIdentification(clearingSystemMemberCode, clearingSystemMemberId, bic);

        // Then
        assertEquals(clearingSystemMemberCode, identification.getClearingSystemMemberCode());
        assertEquals(clearingSystemMemberId, identification.getClearingSystemMemberId());
        assertEquals(bic, identification.getBic());
    }

    @Test
    void agentAddress_AllArgsConstructor() {
        // Given
        String department = "dept";
        String subDepartment = "subdept";
        String streetName = "street";
        String buildingNumber = "123";
        String buildingName = "building";
        String floor = "floor";
        String postBox = "postbox";
        String room = "room";
        String postalCode = "postal";
        String townName = "town";
        String townLocationName = "location";
        String districtName = "district";
        String subdivision = "subdivision";
        String country = "US";
        String email = "email@test.com";

        // When
        AgentAddress address = new AgentAddress(
                department, subDepartment, streetName, buildingNumber, buildingName,
                floor, postBox, room, postalCode, townName, townLocationName,
                districtName, subdivision, country, email
        );

        // Then
        assertEquals(department, address.getDepartment());
        assertEquals(subDepartment, address.getSubDepartment());
        assertEquals(streetName, address.getStreetName());
        assertEquals(buildingNumber, address.getBuildingNumber());
        assertEquals(buildingName, address.getBuildingName());
        assertEquals(floor, address.getFloor());
        assertEquals(postBox, address.getPostBox());
        assertEquals(room, address.getRoom());
        assertEquals(postalCode, address.getPostalCode());
        assertEquals(townName, address.getTownName());
        assertEquals(townLocationName, address.getTownLocationName());
        assertEquals(districtName, address.getDistrictName());
        assertEquals(subdivision, address.getSubdivision());
        assertEquals(country, address.getCountry());
        assertEquals(email, address.getEmail());
    }

    @Test
    void apiResponse_AllArgsConstructor() {
        // Given
        String status = "SUCCESS";
        String message = "Operation completed";
        String counterpartyId = "uuid-123";

        // When
        ApiResponse response = new ApiResponse(status, message, counterpartyId);

        // Then
        assertEquals(status, response.getStatus());
        assertEquals(message, response.getMessage());
        assertEquals(counterpartyId, response.getCounterpartyId());
    }

    @Test
    void apiResponse_JsonSerialization() throws Exception {
        // Given
        ApiResponse response = new ApiResponse("SUCCESS", "Operation completed", "uuid-123");

        // When
        String json = objectMapper.writeValueAsString(response);

        // Then
        assertNotNull(json);
        assertTrue(json.contains("SUCCESS"));
        assertTrue(json.contains("Operation completed"));
        assertTrue(json.contains("uuid-123"));
    }
}
