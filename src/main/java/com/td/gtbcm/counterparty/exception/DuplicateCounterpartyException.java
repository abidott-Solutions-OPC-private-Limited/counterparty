package com.td.gtbcm.counterparty.exception;

public class DuplicateCounterpartyException extends RuntimeException {
    
    public DuplicateCounterpartyException(String message) {
        super(message);
    }
    
    public DuplicateCounterpartyException(String message, Throwable cause) {
        super(message, cause);
    }
}
