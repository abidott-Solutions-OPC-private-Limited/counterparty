package com.td.gtbcm.counterparty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterpartyRequest {
    @JsonProperty("messageId")
    private String messageId;
    
    @JsonProperty("traceabilityId")
    private String traceabilityId;
    
    @JsonProperty("clientId")
    private String clientId;
    
    @JsonProperty("alternatePartyId")
    private String alternatePartyId;
    
    @JsonProperty("partyType")
    private String partyType;
    
    @JsonProperty("partyName")
    private String partyName;
    
    @JsonProperty("partyNickName")
    private String partyNickName;
    
    @JsonProperty("partyOrganizationId")
    private String partyOrganizationId;
    
    @JsonProperty("partyPrivateId")
    private String partyPrivateId;
    
    @JsonProperty("partyAccount")
    private PartyAccount partyAccount;
    
    @JsonProperty("partyAddress")
    private PartyAddress partyAddress;
    
    @JsonProperty("agent")
    private Agent agent;
    
    @JsonProperty("createdBy")
    private String createdBy;
    
    @JsonProperty("approvedBy")
    private String approvedBy;
}
