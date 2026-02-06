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
        Map<Object, Object> emptyMap = new HashMap<>();

        assertTrue(schema.isValid(emptyMap)); // an empty map should be valid

        schema.required();
        assertTrue(schema.isValid(emptyMap)); // an empty map is still valid even when required
    }

    @Test
    void testIsAnyEmptyWithNonEmptyMap() {
        Map<Object, Object> map = new HashMap<>();
        map.put("key", "value");

        assertTrue(schema.isValid(map)); // a non-empty map should be valid

        schema.required();
        assertTrue(schema.isValid(map)); // a non-empty map remains valid when required
    }

    @Test
    void testSizeofWithMatchingSize() {
        Map<Object, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        schema.sizeof(2);
        assertTrue(schema.isValid(map)); // valid because map size is 2
    }

    @Test
    void testSizeofWithNonMatchingSize() {
        Map<Object, Object> map = new HashMap<>();
        map.put("key1", "value1");

        schema.sizeof(2);
        assertFalse(schema.isValid(map)); // invalid because map size is not 2
    }

    @Test
    void testWithEmptyMapAndSizeConstraint() {
        Map<Object, Object> emptyMap = new HashMap<>();

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
    void testChainingRequiredAndSizeof() {
        Map<Object, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        schema.required().sizeof(2);
        assertTrue(schema.isValid(map)); // valid because a map is non-null and the size is 2

        map.put("key3", "value3");
        assertFalse(schema.isValid(map)); // invalid because size is now 3, not 2
    }
}