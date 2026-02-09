package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class MapSchemaTest {
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        schema = new MapSchema();
    }

    @Test
    void testIsAnyEmptyWithNullValue() {
        assertTrue(schema.isValid(null)); // null should be valid

        schema.required();
        assertFalse(schema.isValid(null)); // null should be invalid when required
    }

    @Test
    void testIsAnyEmptyWithEmptyMap() {
        Map<String, String> emptyMap = new HashMap<>();

        assertTrue(schema.isValid(emptyMap)); // an empty map should be valid

        schema.required();
        assertTrue(schema.isValid(emptyMap)); // an empty map is still valid even when required
    }

    @Test
    void testIsAnyEmptyWithNonEmptyMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertTrue(schema.isValid(map)); // a non-empty map should be valid

        schema.required();
        assertTrue(schema.isValid(map)); // a non-empty map remains valid when required
    }

    @Test
    void testSizeofWithMatchingSize() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        schema.sizeof(2);
        assertTrue(schema.isValid(map)); // valid because map size is 2
    }

    @Test
    void testSizeofWithNonMatchingSize() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");

        schema.sizeof(2);
        assertFalse(schema.isValid(map)); // invalid because map size is not 2
    }

    @Test
    void testWithEmptyMapAndSizeConstraint() {
        Map<String, String> emptyMap = new HashMap<>();

        schema.sizeof(0);
        assertTrue(schema.isValid(emptyMap)); // valid because a map is empty and the size is 0
    }

    @Test
    void testWithNullAndSizeConstraint() {
        schema.sizeof(1);
        assertTrue(schema.isValid(null)); // valid because null is treated as empty without required
    }

    @Test
    void testWithIntKeyAndObjectValue() {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, "value");

        schema.required().sizeof(1);
        assertTrue(schema.isValid(map)); // valid

        map.put(2, "value2");
        assertFalse(schema.isValid(map)); // invalid because size is now 2, not 1
    }

    @Test
    void testMapShapes() {
        Validator v = new Validator();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required()); // the firstName is required
        schemas.put("lastName", v.string().required().minLength(2)); // lastName is required and at least 2 chars long

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }

    @Test
    void testChainingRequiredAndSizeof() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        schema.required().sizeof(2);
        assertTrue(schema.isValid(map)); // valid because a map is non-null and the size is 2

        map.put("key3", "value3");
        assertFalse(schema.isValid(map)); // invalid because size is now 3, not 2
    }
}