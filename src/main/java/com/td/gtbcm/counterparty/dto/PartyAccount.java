package com.td.gtbcm.counterparty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyAccount {
    @JsonProperty("iban")
    private String iban;
    
    @JsonProperty("otherId")
    private String otherId;
    
    @JsonProperty("currency")
    private String currency;
}
