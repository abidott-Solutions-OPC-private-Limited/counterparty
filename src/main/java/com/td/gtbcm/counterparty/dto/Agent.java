package com.td.gtbcm.counterparty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    @JsonProperty("agentName")
    private String agentName;
    
    @JsonProperty("identification")
    private AgentIdentification identification;
    
    @JsonProperty("address")
    private AgentAddress address;
}
