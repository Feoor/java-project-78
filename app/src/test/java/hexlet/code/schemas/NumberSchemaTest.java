package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class NumberSchemaTest {
    private NumberSchema schema;
    private static final int MIN_VALUE = 10;
    private static final int MAX_VALUE = 20;

    @BeforeEach
    void setUp() {
        schema = new NumberSchema();
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 15, 20})
    void testRangeWithinBounds(int value) {
        schema.range(MIN_VALUE, MAX_VALUE);
        assertTrue(schema.isValid(value)); // 10, 15, and 20 are within the range, valid
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 25})
    void testRangeWithinBoundsNegative(int value) {
        schema.range(MIN_VALUE, MAX_VALUE);
        assertFalse(schema.isValid(value)); // 5 and 25 are within the range, invalid
    }

    @Test
    void testRangeWithNegativeBounds() {
        schema.range(-10, 10);
        assertTrue(schema.isValid(0)); // 0 falls within -10 and 10
        assertTrue(schema.isValid(-10)); // -10 is the lower bound
        assertTrue(schema.isValid(10)); // 10 is the upper bound
        assertFalse(schema.isValid(-15)); // -15 is below -10
        assertFalse(schema.isValid(15)); // 15 is above 10
    }

    @Test
    void testRangeWithFloatingPointNumbers() {
        schema.range(0.5, 2.5);
        assertTrue(schema.isValid(1.5)); // 1.5 is within 0.5 and 2.5
        assertTrue(schema.isValid(0.5)); // 0.5 is the lower bound
        assertTrue(schema.isValid(2.5)); // 2.5 is the upper bound
        assertFalse(schema.isValid(0.4)); // 0.4 is below the lower bound
        assertFalse(schema.isValid(2.6)); // 2.6 is above the upper bound
    }

    @Test
    void testWithNullValue() {
        assertTrue(schema.isValid(null)); // null should be valid

        schema.required();
        assertFalse(schema.isValid(null)); // null should be invalid
    }

    @Test
    void testChaining() {
        schema.required().positive().range(1, 100);

        assertTrue(schema.isValid(50)); // valid
        assertFalse(schema.isValid(-10)); // invalid, not positive
        assertFalse(schema.isValid(0)); // invalid, not positive
        assertFalse(schema.isValid(101)); // invalid, above upper bound
    }

    @Test
    void testInvalidRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> schema.range(10, 5));
        assertEquals("Min value must be less than max value", exception.getMessage());
    }
}
