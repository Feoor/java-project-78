package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private StringSchema schema;
    private static final String text = "what does the fox say";

    @BeforeEach
    void setUp() {
        schema = new StringSchema();
    }

    @Test
    void testStringSchema() {
        schema.required();
        assertTrue(schema.isValid(text)); // true

        schema.minLength(5).contains("hex");
        assertTrue(schema.isValid("hexlet")); // true

        assertTrue(schema.contains("wh").isValid(text)); // true
        assertTrue(schema.contains("what").isValid(text)); // true
        assertFalse(schema.contains("whatthe").isValid(text)); // false
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
    void testWithOverwrite() {
        assertTrue(schema.minLength(10).minLength(5).isValid("Hexlet")); // true
        assertTrue(schema.contains("hex").contains("what").isValid(text)); // true

        assertFalse(schema.minLength(4).minLength(10).isValid("Hexlet")); // false
        assertFalse(schema.contains("wh").contains("whatthe").isValid(text)); // false
    }

    @Test
    void testWithEmptyContains() {
        assertTrue(schema.contains("").isValid(text));
    }

    @Test
    void testWithTwoSchemes() {
        schema.required();
        StringSchema schema2 = schema.minLength(5);

        assertFalse(schema2.isValid("one")); // false
        assertFalse(schema.isValid("one")); // false
    }
}
