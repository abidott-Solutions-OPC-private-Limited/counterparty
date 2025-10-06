package com.gtbcm.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentAddress {
    @JsonProperty("department")
    private String department;
    
    @JsonProperty("subDepartment")
    private String subDepartment;
    
    @JsonProperty("streetName")
    private String streetName;
    
    @JsonProperty("buildingNumber")
    private String buildingNumber;
    
    @JsonProperty("buildingName")
    private String buildingName;
    
    @JsonProperty("floor")
    private String floor;
    
    @JsonProperty("postBox")
    private String postBox;
    
    @JsonProperty("room")
    private String room;
    
    @JsonProperty("postalCode")
    private String postalCode;
    
    @JsonProperty("townName")
    private String townName;
    
    @JsonProperty("townLocationName")
    private String townLocationName;
    
    @JsonProperty("districtName")
    private String districtName;
    
    @JsonProperty("subdivision")
    private String subdivision;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("email")
    private String email;
}
