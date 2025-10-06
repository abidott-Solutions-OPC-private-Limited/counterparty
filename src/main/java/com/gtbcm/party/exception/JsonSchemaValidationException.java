package com.gtbcm.party.exception;

import java.util.List;

public class JsonSchemaValidationException extends RuntimeException {
    
    private final List<String> validationErrors;
    
    public JsonSchemaValidationException(String message) {
        super(message);
        this.validationErrors = List.of(message);
    }
    
    public JsonSchemaValidationException(List<String> validationErrors) {
        super("JSON Schema validation failed: " + String.join(", ", validationErrors));
        this.validationErrors = validationErrors;
    }
    
    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
