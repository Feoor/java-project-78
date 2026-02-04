package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ValidatorTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testStringSchemaCreation() {
        StringSchema schema = validator.string();
        assertInstanceOf(StringSchema.class, schema);

        schema.required();
        assertTrue(schema.isValid("Hello")); // true
        assertFalse(schema.isValid("")); // false
    }
}
