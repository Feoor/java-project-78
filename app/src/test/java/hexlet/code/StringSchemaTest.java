package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StringSchemaTest {
    private StringSchema schema;
    private static final String TEXT = "what does the fox say";
    private static final int MIN_LENGTH = 5;
    private static final int OVERWRITE_LENGTH = 10;

    @BeforeEach
    void setUp() {
        schema = new StringSchema();
    }

    @Test
    void testStringSchema() {
        schema.required();
        assertTrue(schema.isValid(TEXT)); // true

        schema.minLength(MIN_LENGTH).contains("hex");
        assertTrue(schema.isValid("hexlet")); // true

        assertTrue(schema.contains("wh").isValid(TEXT)); // true
        assertTrue(schema.contains("what").isValid(TEXT)); // true
        assertFalse(schema.contains("whatthe").isValid(TEXT)); // false
        assertFalse(schema.isValid("")); // false
    }

    @Test
    void testWithEmptyValues() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testChain() {
        assertTrue(schema.required().minLength(MIN_LENGTH).contains("hex").isValid("hexlet")); // true
        assertFalse(schema.required().minLength(MIN_LENGTH).contains("what").isValid("what")); // false by minLength
    }

    @Test
    void testWithOverwrite() {
        assertTrue(schema.minLength(OVERWRITE_LENGTH).minLength(MIN_LENGTH).isValid("Hexlet")); // true
        assertTrue(schema.contains("hex").contains("what").isValid(TEXT)); // true

        assertFalse(schema.minLength(MIN_LENGTH).minLength(OVERWRITE_LENGTH).isValid("Hexlet")); // false
        assertFalse(schema.contains("wh").contains("whatthe").isValid(TEXT)); // false
    }

    @Test
    void testWithEmptyContains() {
        assertTrue(schema.contains("").isValid(TEXT));
    }

    @Test
    void testWithTwoSchemes() {
        schema.required();
        StringSchema schema2 = schema.minLength(MIN_LENGTH);

        assertFalse(schema2.isValid("one")); // false
        assertFalse(schema.isValid("one")); // false
    }
}
