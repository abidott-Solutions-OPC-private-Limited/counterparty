package com.gtbcm.party.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtbcm.party.dto.CounterpartyRequest;
import com.gtbcm.party.entity.CounterpartyEntity;
import com.gtbcm.party.exception.DuplicateCounterpartyException;
import com.gtbcm.party.exception.JsonSchemaValidationException;
import com.gtbcm.party.repository.CounterpartyRepository;
import com.gtbcm.party.service.CounterpartyService;
import com.gtbcm.party.service.JsonSchemaValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;
    private final ObjectMapper objectMapper;
    private final JsonSchemaValidatorService jsonSchemaValidatorService;

    @Override
    @Transactional
    public String createCounterparty(CounterpartyRequest request) {
        log.info("Creating counterparty for clientId: {}", request.getClientId());
        
        // Convert request to JsonNode for schema validation
        JsonNode requestJsonNode = objectMapper.valueToTree(request);
        
        // Validate using JSON schema
        try {
            jsonSchemaValidatorService.schemaValidation(requestJsonNode);
        } catch (JsonSchemaValidationException e) {
            log.error("JSON Schema validation failed for clientId: {}", request.getClientId(), e);
            throw e;
        }
        
        // Check for duplicates
        checkForDuplicates(request);
        
        // Generate UUID for counterparty_id
        String counterpartyId = UUID.randomUUID().toString();
        
        // Map DTO to Entity
        CounterpartyEntity entity = mapRequestToEntity(request, counterpartyId);
        
        // Store full JSON request
        try {
            String jsonRequest = objectMapper.writeValueAsString(request);
            entity.setJsonRequest(jsonRequest);
        } catch (JsonProcessingException e) {
            log.error("Error serializing request to JSON", e);
            throw new RuntimeException("Error processing request", e);
        }
        
        // Set additional fields
        entity.setEnabled(true);
        entity.setCreatedDatetime(LocalDateTime.now());
        
        // Save entity
        CounterpartyEntity savedEntity = counterpartyRepository.save(entity);
        
        log.info("Counterparty created successfully with ID: {}", savedEntity.getCounterpartyId());
        return savedEntity.getCounterpartyId();
    }


    private void checkForDuplicates(CounterpartyRequest request) {
        String iban = request.getPartyAccount().getIban();
        String otherId = request.getPartyAccount().getOtherId();
        
        Optional<CounterpartyEntity> existing = counterpartyRepository.findActiveDuplicate(
            request.getClientId(),
            request.getPartyName(),
            iban,
            otherId
        );
        
        if (existing.isPresent()) {
            throw new DuplicateCounterpartyException(
                String.format("Counterparty already exists for ClientId %s with PartyName %s and account details", 
                    request.getClientId(), request.getPartyName())
            );
        }
    }

    private CounterpartyEntity mapRequestToEntity(CounterpartyRequest request, String counterpartyId) {
        return CounterpartyEntity.builder()
            .counterpartyId(counterpartyId)
            .clientId(request.getClientId())
            .alternatePartyId(request.getAlternatePartyId())
            .partyType(request.getPartyType())
            .partyName(request.getPartyName())
            .partyNickName(request.getPartyNickName())
            .partyOrgId(request.getPartyOrganizationId())
            .partyPrivateId(request.getPartyPrivateId())
            .partyAccountIban(request.getPartyAccount().getIban())
            .partyAccountOtherId(request.getPartyAccount().getOtherId())
            .partyAccountCurrency(request.getPartyAccount().getCurrency())
            .partyAddrDepartment(request.getPartyAddress().getDepartment())
            .partyAddrSubDepartment(request.getPartyAddress().getSubDepartment())
            .partyAddrStreetName(request.getPartyAddress().getStreetName())
            .partyAddrBuildingNo(request.getPartyAddress().getBuildingNumber())
            .partyAddrBuildingName(request.getPartyAddress().getBuildingName())
            .partyAddrFloor(request.getPartyAddress().getFloor())
            .partyAddrTownLocationName(request.getPartyAddress().getTownLocationName())
            .partyAddrDistrictName(request.getPartyAddress().getDistrictName())
            .partyAddrSubdivision(request.getPartyAddress().getSubdivision())
            .partyAddrCountry(request.getPartyAddress().getCountry())
            .partyEmail(request.getPartyAddress().getEmail())
            .agentName(request.getAgent().getAgentName())
            .agentClearingSysMemberCode(request.getAgent().getIdentification() != null ? 
                request.getAgent().getIdentification().getClearingSystemMemberCode() : null)
            .agentBic(request.getAgent().getIdentification() != null ? 
                request.getAgent().getIdentification().getBic() : null)
            .agentClearingSysMemberId(request.getAgent().getIdentification() != null ? 
                request.getAgent().getIdentification().getClearingSystemMemberId() : null)
            .agentAddrDepartment(request.getAgent().getAddress().getDepartment())
            .agentAddrSubDepartment(request.getAgent().getAddress().getSubDepartment())
            .agentAddrStreetName(request.getAgent().getAddress().getStreetName())
            .agentAddrBuildingNo(request.getAgent().getAddress().getBuildingNumber())
            .agentAddrBuildingName(request.getAgent().getAddress().getBuildingName())
            .agentAddrFloor(request.getAgent().getAddress().getFloor())
            .agentAddrPostBox(request.getAgent().getAddress().getPostBox())
            .agentAddrRoom(request.getAgent().getAddress().getRoom())
            .agentAddrPostalCode(request.getAgent().getAddress().getPostalCode())
            .agentAddrTownName(request.getAgent().getAddress().getTownName())
            .agentAddrTownLocationName(request.getAgent().getAddress().getTownLocationName())
            .agentAddrDistrictName(request.getAgent().getAddress().getDistrictName())
            .agentAddrSubdivision(request.getAgent().getAddress().getSubdivision())
            .agentAddrCountry(request.getAgent().getAddress().getCountry())
            .agentEmail(request.getAgent().getAddress().getEmail())
            .createdBy(request.getCreatedBy())
            .approvedBy(request.getApprovedBy())
            .build();
    }
}
