package com.td.gtbcm.counterparty.exception;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {

    @Test
    void duplicateCounterpartyException_WithMessage() {
        // Given
        String message = "Duplicate counterparty found";

        // When
        DuplicateCounterpartyException exception = new DuplicateCounterpartyException(message);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void duplicateCounterpartyException_WithMessageAndCause() {
        // Given
        String message = "Duplicate counterparty found";
        RuntimeException cause = new RuntimeException("Database error");

        // When
        DuplicateCounterpartyException exception = new DuplicateCounterpartyException(message, cause);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void duplicateCounterpartyException_WithNullMessage() {
        // Given
        String message = null;

        // When
        DuplicateCounterpartyException exception = new DuplicateCounterpartyException(message);

        // Then
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void duplicateCounterpartyException_WithEmptyMessage() {
        // Given
        String message = "";

        // When
        DuplicateCounterpartyException exception = new DuplicateCounterpartyException(message);

        // Then
        assertNotNull(exception);
        assertEquals("", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void jsonSchemaValidationException_WithMessage() {
        // Given
        String message = "Validation failed";

        // When
        JsonSchemaValidationException exception = new JsonSchemaValidationException(message);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
        assertNotNull(exception.getValidationErrors());
        assertEquals(1, exception.getValidationErrors().size());
        assertEquals(message, exception.getValidationErrors().get(0));
    }

    @Test
    void jsonSchemaValidationException_WithValidationErrors() {
        // Given
        List<String> validationErrors = List.of("Field is required", "Invalid format", "Missing value");

        // When
        JsonSchemaValidationException exception = new JsonSchemaValidationException(validationErrors);

        // Then
        assertNotNull(exception);
        assertEquals("JSON Schema validation failed: Field is required, Invalid format, Missing value", exception.getMessage());
        assertNull(exception.getCause());
        assertNotNull(exception.getValidationErrors());
        assertEquals(3, exception.getValidationErrors().size());
        assertEquals(validationErrors, exception.getValidationErrors());
    }

    @Test
    void jsonSchemaValidationException_WithEmptyValidationErrors() {
        // Given
        List<String> validationErrors = List.of();

        // When
        JsonSchemaValidationException exception = new JsonSchemaValidationException(validationErrors);

        // Then
        assertNotNull(exception);
        assertEquals("JSON Schema validation failed: ", exception.getMessage());
        assertNull(exception.getCause());
        assertNotNull(exception.getValidationErrors());
        assertTrue(exception.getValidationErrors().isEmpty());
    }


    @Test
    void jsonSchemaValidationException_WithEmptyMessage() {
        // Given
        String message = "";

        // When
        JsonSchemaValidationException exception = new JsonSchemaValidationException(message);

        // Then
        assertNotNull(exception);
        assertEquals("", exception.getMessage());
        assertNull(exception.getCause());
        assertNotNull(exception.getValidationErrors());
        assertEquals(1, exception.getValidationErrors().size());
        assertEquals("", exception.getValidationErrors().get(0));
    }

    @Test
    void jsonSchemaValidationException_WithSingleValidationError() {
        // Given
        String singleError = "Client ID is required";

        // When
        JsonSchemaValidationException exception = new JsonSchemaValidationException(singleError);

        // Then
        assertNotNull(exception);
        assertEquals(singleError, exception.getMessage());
        assertNull(exception.getCause());
        assertNotNull(exception.getValidationErrors());
        assertEquals(1, exception.getValidationErrors().size());
        assertEquals(singleError, exception.getValidationErrors().get(0));
    }

    @Test
    void jsonSchemaValidationException_WithMultipleValidationErrors() {
        // Given
        List<String> validationErrors = List.of(
                "Client ID is required",
                "Party name is required",
                "Invalid IBAN format",
                "Country code must be 2 characters"
        );

        // When
        JsonSchemaValidationException exception = new JsonSchemaValidationException(validationErrors);

        // Then
        assertNotNull(exception);
        assertEquals("JSON Schema validation failed: Client ID is required, Party name is required, Invalid IBAN format, Country code must be 2 characters", exception.getMessage());
        assertNull(exception.getCause());
        assertNotNull(exception.getValidationErrors());
        assertEquals(4, exception.getValidationErrors().size());
        assertEquals(validationErrors, exception.getValidationErrors());
    }


}
