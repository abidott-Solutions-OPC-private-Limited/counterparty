package com.td.gtbcm.counterparty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "schema")
public class SchemaProperties {
    
    private String counterpartySchemaPath = "schemas/counterparty-request-schema.json";
    
    public String counterpartySchemaPath() {
        return counterpartySchemaPath;
    }
    
    public void setCounterpartySchemaPath(String counterpartySchemaPath) {
        this.counterpartySchemaPath = counterpartySchemaPath;
    }
}
