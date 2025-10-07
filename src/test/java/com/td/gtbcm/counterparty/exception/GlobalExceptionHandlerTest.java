package com.td.gtbcm.counterparty.exception;

import com.td.gtbcm.counterparty.dto.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void handleDuplicateCounterpartyException_ReturnsBadRequest() {
        // Given
        String errorMessage = "Duplicate counterparty found";
        DuplicateCounterpartyException exception = new DuplicateCounterpartyException(errorMessage);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleDuplicateCounterpartyException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ERROR", response.getBody().getStatus());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertNull(response.getBody().getCounterpartyId());
    }

    @Test
    void handleJsonSchemaValidationException_ReturnsBadRequest() {
        // Given
        List<String> validationErrors = List.of("Field is required", "Invalid format");
        JsonSchemaValidationException exception = new JsonSchemaValidationException(validationErrors);

        // When
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleJsonSchemaValidationException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ERROR", response.getBody().get("status"));
        assertEquals("JSON Schema validation failed", response.getBody().get("message"));
        assertEquals(validationErrors, response.getBody().get("validationErrors"));
    }

    @Test
    void handleJsonSchemaValidationException_SingleError_ReturnsBadRequest() {
        // Given
        String errorMessage = "Single validation error";
        JsonSchemaValidationException exception = new JsonSchemaValidationException(errorMessage);

        // When
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleJsonSchemaValidationException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ERROR", response.getBody().get("status"));
        assertEquals("JSON Schema validation failed", response.getBody().get("message"));
        assertTrue(response.getBody().get("validationErrors") instanceof List);
        List<?> errors = (List<?>) response.getBody().get("validationErrors");
        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0));
    }

    @Test
    void handleValidationExceptions_ReturnsBadRequest() {
        // Given
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError1 = new FieldError("counterpartyRequest", "clientId", "Client ID is required");
        FieldError fieldError2 = new FieldError("counterpartyRequest", "partyName", "Party name is required");
        
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError1, fieldError2));

        // When
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleValidationExceptions(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ERROR", response.getBody().get("status"));
        assertEquals("Validation failed", response.getBody().get("message"));
        
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) response.getBody().get("errors");
        assertNotNull(errors);
        assertEquals("Client ID is required", errors.get("clientId"));
        assertEquals("Party name is required", errors.get("partyName"));
    }

    @Test
    void handleValidationExceptions_EmptyErrors_ReturnsBadRequest() {
        // Given
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of());

        // When
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleValidationExceptions(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ERROR", response.getBody().get("status"));
        assertEquals("Validation failed", response.getBody().get("message"));
        
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) response.getBody().get("errors");
        assertNotNull(errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void handleGenericException_ReturnsInternalServerError() {
        // Given
        String errorMessage = "Database connection failed";
        Exception exception = new RuntimeException(errorMessage);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleGenericException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ERROR", response.getBody().getStatus());
        assertTrue(response.getBody().getMessage().contains("An unexpected error occurred"));
        assertTrue(response.getBody().getMessage().contains(errorMessage));
        assertNull(response.getBody().getCounterpartyId());
    }

    @Test
    void handleGenericException_NullMessage_ReturnsInternalServerError() {
        // Given
        Exception exception = new RuntimeException();

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleGenericException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ERROR", response.getBody().getStatus());
        assertTrue(response.getBody().getMessage().contains("An unexpected error occurred"));
        assertNull(response.getBody().getCounterpartyId());
    }

    @Test
    void handleGenericException_NullException_ReturnsInternalServerError() {
        // Given
        Exception exception = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            globalExceptionHandler.handleGenericException(exception);
        });
    }
}
