package com.td.gtbcm.counterparty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentIdentification {
    @JsonProperty("clearingSystemMemberCode")
    private String clearingSystemMemberCode;
    
    @JsonProperty("clearingSystemMemberId")
    private String clearingSystemMemberId;
    
    @JsonProperty("bic")
    private String bic;
}
