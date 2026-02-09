package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ValidatorTest {
    private Validator validator;
    private static final int MIN_LENGTH = 5;

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

    @Test
    void testNumberSchemaCreation() {
        NumberSchema schema = validator.number();
        assertInstanceOf(NumberSchema.class, schema);

        schema.required().positive();
        assertTrue(schema.isValid(1)); // true
        assertFalse(schema.isValid(-1)); // false
    }

    @Test
    void testMapSchemaCreation() {
        MapSchema schema = validator.map();
        assertInstanceOf(MapSchema.class, schema);

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value2");
        map.put("key2", "value2");

        schema.required().sizeof(2);
        assertTrue(schema.isValid(map)); // true
        assertFalse(schema.isValid(new HashMap<>())); // false
    }

    @Test
    void testWithTwoSchemes() {
        StringSchema schema = validator.string();
        schema.required();
        StringSchema schema2 = schema.minLength(MIN_LENGTH);

        assertFalse(schema2.isValid("one")); // invalid
        assertFalse(schema.isValid("one")); // invalid
    }
}
