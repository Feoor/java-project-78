package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class StringSchemaTest {
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
        assertTrue(schema.isValid(TEXT)); // valid

        schema.minLength(MIN_LENGTH).contains("hex");
        assertTrue(schema.isValid("hexlet")); // valid, contains hex

        assertTrue(schema.contains("wh").isValid(TEXT)); // valid, contains wh
        assertTrue(schema.contains("what").isValid(TEXT)); // valid, contains what
        assertFalse(schema.contains("whatthe").isValid(TEXT)); // invalid, does not contain whatthe
        assertFalse(schema.isValid("")); // invalid, empty string when .required()
    }

    @Test
    void testWithEmptyValues() {
        assertTrue(schema.isValid("")); // valid
        assertTrue(schema.isValid(null)); // valid

        schema.required();
        assertFalse(schema.isValid("")); // invalid
        assertFalse(schema.isValid(null)); // invalid
    }

    @Test
    void testChaining() {
        assertTrue(schema.required().minLength(MIN_LENGTH).contains("hex").isValid("hexlet")); // valid
        assertFalse(schema.required().minLength(MIN_LENGTH).contains("what").isValid("what")); // invalid by minLength
    }

    @Test
    void testWithOverwrite() {
        assertTrue(schema.minLength(OVERWRITE_LENGTH).minLength(MIN_LENGTH).isValid("Hexlet")); // valid
        assertTrue(schema.contains("hex").contains("what").isValid(TEXT)); // valid

        assertFalse(schema.minLength(MIN_LENGTH).minLength(OVERWRITE_LENGTH).isValid("Hexlet")); // invalid
        assertFalse(schema.contains("wh").contains("whatthe").isValid(TEXT)); // invalid
    }

    @Test
    void testWithEmptyContains() {
        assertTrue(schema.contains("").isValid(TEXT));
    }
}
