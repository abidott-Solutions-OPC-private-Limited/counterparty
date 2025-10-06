package com.td.gtbcm.counterparty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "counterparties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CounterpartyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cp_seq_id")
    private Long cpSeqId;
    
    @Column(name = "counterparty_id", nullable = false, length = 36)
    private String counterpartyId;
    
    @Column(name = "client_id", nullable = false, length = 140)
    private String clientId;
    
    @Column(name = "alternate_party_id", length = 140)
    private String alternatePartyId;
    
    @Column(name = "party_type", nullable = false, length = 15)
    private String partyType;
    
    @Column(name = "party_name", nullable = false, length = 140)
    private String partyName;
    
    @Column(name = "party_nick_name", length = 140)
    private String partyNickName;
    
    @Column(name = "party_org_id", length = 35)
    private String partyOrgId;
    
    @Column(name = "party_private_id", length = 35)
    private String partyPrivateId;
    
    @Column(name = "party_account_iban", length = 30)
    private String partyAccountIban;
    
    @Column(name = "party_account_other_id", length = 34)
    private String partyAccountOtherId;
    
    @Column(name = "party_account_currency", length = 3)
    private String partyAccountCurrency;
    
    @Column(name = "party_addr_department", length = 70)
    private String partyAddrDepartment;
    
    @Column(name = "party_addr_sub_department", length = 70)
    private String partyAddrSubDepartment;
    
    @Column(name = "party_addr_street_name", nullable = false, length = 70)
    private String partyAddrStreetName;
    
    @Column(name = "party_addr_building_no", nullable = false, length = 16)
    private String partyAddrBuildingNo;
    
    @Column(name = "party_addr_building_name", length = 35)
    private String partyAddrBuildingName;
    
    @Column(name = "party_addr_floor", length = 70)
    private String partyAddrFloor;
    
    @Column(name = "party_addr_town_location_name", length = 35)
    private String partyAddrTownLocationName;
    
    @Column(name = "party_addr_district_name", length = 35)
    private String partyAddrDistrictName;
    
    @Column(name = "party_addr_subdivision", nullable = false, length = 35)
    private String partyAddrSubdivision;
    
    @Column(name = "party_addr_country", nullable = false, length = 2)
    private String partyAddrCountry;
    
    @Column(name = "party_phone_number", length = 30)
    private String partyPhoneNumber;
    
    @Column(name = "party_email", length = 2048)
    private String partyEmail;
    
    @Column(name = "agent_name", nullable = false, length = 140)
    private String agentName;
    
    @Column(name = "agent_clearing_sys_member_code", length = 15)
    private String agentClearingSysMemberCode;
    
    @Column(name = "agent_bic", length = 11)
    private String agentBic;
    
    @Column(name = "agent_clearing_sys_member_id", length = 35)
    private String agentClearingSysMemberId;
    
    @Column(name = "agent_addr_department", length = 70)
    private String agentAddrDepartment;
    
    @Column(name = "agent_addr_sub_department", length = 70)
    private String agentAddrSubDepartment;
    
    @Column(name = "agent_addr_street_name", length = 70)
    private String agentAddrStreetName;
    
    @Column(name = "agent_addr_building_no", length = 16)
    private String agentAddrBuildingNo;
    
    @Column(name = "agent_addr_building_name", length = 35)
    private String agentAddrBuildingName;
    
    @Column(name = "agent_addr_floor", length = 70)
    private String agentAddrFloor;
    
    @Column(name = "agent_addr_post_box", length = 16)
    private String agentAddrPostBox;
    
    @Column(name = "agent_addr_room", length = 70)
    private String agentAddrRoom;
    
    @Column(name = "agent_addr_postal_code", length = 16)
    private String agentAddrPostalCode;
    
    @Column(name = "agent_addr_town_name", length = 35)
    private String agentAddrTownName;
    
    @Column(name = "agent_addr_town_location_name", length = 35)
    private String agentAddrTownLocationName;
    
    @Column(name = "agent_addr_district_name", length = 35)
    private String agentAddrDistrictName;
    
    @Column(name = "agent_addr_subdivision", nullable = false, length = 35)
    private String agentAddrSubdivision;
    
    @Column(name = "agent_addr_country", nullable = false, length = 2)
    private String agentAddrCountry;
    
    @Column(name = "agent_phone_number", length = 30)
    private String agentPhoneNumber;
    
    @Column(name = "agent_email", length = 2048)
    private String agentEmail;
    
    @Column(name = "created_by", nullable = false, length = 140)
    private String createdBy;
    
    @Column(name = "approved_by", nullable = false, length = 140)
    private String approvedBy;
    
    @Column(name = "last_modified_by", length = 140)
    private String lastModifiedBy;
    
    @Column(name = "created_datetime", nullable = false)
    private LocalDateTime createdDatetime;
    
    @Column(name = "last_modified_datetime")
    private LocalDateTime lastModifiedDatetime;
    
    @Column(name = "json_request", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String jsonRequest;
    
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}
