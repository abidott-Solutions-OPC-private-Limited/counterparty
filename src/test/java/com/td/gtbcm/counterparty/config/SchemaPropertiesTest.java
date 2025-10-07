package com.td.gtbcm.counterparty.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchemaPropertiesTest {

    private SchemaProperties schemaProperties;

    @BeforeEach
    void setUp() {
        schemaProperties = new SchemaProperties();
    }

    @Test
    void counterpartySchemaPath_DefaultValue() {
        // When
        String result = schemaProperties.counterpartySchemaPath();

        // Then
        assertNotNull(result);
        assertEquals("schemas/counterparty-request-schema.json", result);
    }

    @Test
    void setCounterpartySchemaPath_ValidPath() {
        // Given
        String newPath = "custom/schema/path.json";

        // When
        schemaProperties.setCounterpartySchemaPath(newPath);

        // Then
        assertEquals(newPath, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_NullPath() {
        // Given
        String nullPath = null;

        // When
        schemaProperties.setCounterpartySchemaPath(nullPath);

        // Then
        assertNull(schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_EmptyPath() {
        // Given
        String emptyPath = "";

        // When
        schemaProperties.setCounterpartySchemaPath(emptyPath);

        // Then
        assertEquals("", schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WhitespacePath() {
        // Given
        String whitespacePath = "   ";

        // When
        schemaProperties.setCounterpartySchemaPath(whitespacePath);

        // Then
        assertEquals("   ", schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_MultipleTimes() {
        // Given
        String firstPath = "first/path.json";
        String secondPath = "second/path.json";
        String thirdPath = "third/path.json";

        // When
        schemaProperties.setCounterpartySchemaPath(firstPath);
        assertEquals(firstPath, schemaProperties.counterpartySchemaPath());

        schemaProperties.setCounterpartySchemaPath(secondPath);
        assertEquals(secondPath, schemaProperties.counterpartySchemaPath());

        schemaProperties.setCounterpartySchemaPath(thirdPath);

        // Then
        assertEquals(thirdPath, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_SamePath() {
        // Given
        String path = "same/path.json";

        // When
        schemaProperties.setCounterpartySchemaPath(path);
        schemaProperties.setCounterpartySchemaPath(path);

        // Then
        assertEquals(path, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithSpecialCharacters() {
        // Given
        String pathWithSpecialChars = "schemas/counterparty-request-schema_v1.0.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithSpecialChars);

        // Then
        assertEquals(pathWithSpecialChars, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithSpaces() {
        // Given
        String pathWithSpaces = "schemas/counterparty request schema.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithSpaces);

        // Then
        assertEquals(pathWithSpaces, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithNumbers() {
        // Given
        String pathWithNumbers = "schemas/schema_v2.1.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithNumbers);

        // Then
        assertEquals(pathWithNumbers, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithHyphens() {
        // Given
        String pathWithHyphens = "schemas/counterparty-request-schema.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithHyphens);

        // Then
        assertEquals(pathWithHyphens, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithUnderscores() {
        // Given
        String pathWithUnderscores = "schemas/counterparty_request_schema.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithUnderscores);

        // Then
        assertEquals(pathWithUnderscores, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithDots() {
        // Given
        String pathWithDots = "schemas.counterparty.request.schema.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithDots);

        // Then
        assertEquals(pathWithDots, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithSlashes() {
        // Given
        String pathWithSlashes = "schemas/subfolder/counterparty-request-schema.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithSlashes);

        // Then
        assertEquals(pathWithSlashes, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithBackslashes() {
        // Given
        String pathWithBackslashes = "schemas\\subfolder\\counterparty-request-schema.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithBackslashes);

        // Then
        assertEquals(pathWithBackslashes, schemaProperties.counterpartySchemaPath());
    }

    @Test
    void setCounterpartySchemaPath_WithMixedSeparators() {
        // Given
        String pathWithMixedSeparators = "schemas/subfolder\\counterparty-request_schema.json";

        // When
        schemaProperties.setCounterpartySchemaPath(pathWithMixedSeparators);

        // Then
        assertEquals(pathWithMixedSeparators, schemaProperties.counterpartySchemaPath());
    }
}
