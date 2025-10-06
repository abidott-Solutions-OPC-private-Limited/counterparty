package com.gtbcm.party.repository;

import com.gtbcm.party.entity.CounterpartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterpartyRepository extends JpaRepository<CounterpartyEntity, Long> {
    
    @Query("SELECT c FROM CounterpartyEntity c WHERE c.clientId = :clientId AND c.partyName = :partyName AND (c.partyAccountIban = :iban OR c.partyAccountOtherId = :otherId) AND c.enabled = true")
    Optional<CounterpartyEntity> findActiveDuplicate(@Param("clientId") String clientId,
                                                     @Param("partyName") String partyName,
                                                     @Param("iban") String iban,
                                                     @Param("otherId") String otherId);
}
