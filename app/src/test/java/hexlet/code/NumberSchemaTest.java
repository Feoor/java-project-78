package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTest {
    private static final int MIN_VALUE = 10;
    private static final int MAX_VALUE = 20;

    @Test
    void testRangeWithinBounds() {
        NumberSchema schema = new NumberSchema();
        schema.range(MIN_VALUE, MAX_VALUE);
        assertTrue(schema.isValid(15)); // 15 is within 10 and 20
    }

    @Test
    void testRangeAtLowerBound() {
        NumberSchema schema = new NumberSchema();
        schema.range(MIN_VALUE, MAX_VALUE);
        assertTrue(schema.isValid(10)); // 10 is the lower bound, valid
    }

    @Test
    void testRangeAtUpperBound() {
        NumberSchema schema = new NumberSchema();
        schema.range(MIN_VALUE, MAX_VALUE);
        assertTrue(schema.isValid(20)); // 20 is the upper bound, valid
    }

    @Test
    void testRangeBelowBounds() {
        NumberSchema schema = new NumberSchema();
        schema.range(MIN_VALUE, MAX_VALUE);
        assertFalse(schema.isValid(5)); // 5 is below the lower bound, invalid
    }

    @Test
    void testRangeAboveBounds() {
        NumberSchema schema = new NumberSchema();
        schema.range(MIN_VALUE, MAX_VALUE);
        assertFalse(schema.isValid(25)); // 25 is above the upper bound, invalid
    }

    @Test
    void testRangeWithNegativeBounds() {
        NumberSchema schema = new NumberSchema();
        schema.range(-10, 10);
        assertTrue(schema.isValid(0)); // 0 falls within -10 and 10
        assertTrue(schema.isValid(-10)); // -10 is the lower bound
        assertTrue(schema.isValid(10)); // 10 is the upper bound
        assertFalse(schema.isValid(-15)); // -15 is below -10
        assertFalse(schema.isValid(15)); // 15 is above 10
    }

    @Test
    void testRangeWithFloatingPointNumbers() {
        NumberSchema schema = new NumberSchema();
        schema.range(0.5, 2.5);
        assertTrue(schema.isValid(1.5)); // 1.5 is within 0.5 and 2.5
        assertTrue(schema.isValid(0.5)); // 0.5 is the lower bound
        assertTrue(schema.isValid(2.5)); // 2.5 is the upper bound
        assertFalse(schema.isValid(0.4)); // 0.4 is below the lower bound
        assertFalse(schema.isValid(2.6)); // 2.6 is above the upper bound
    }

    @Test
    void testWithNullValue() {
        NumberSchema schema = new NumberSchema();

        assertTrue(schema.isValid(null)); // null should be valid

        schema.required();
        assertFalse(schema.isValid(null)); // null should be invalid
    }

    @Test
    void testChaining() {
        NumberSchema schema = new NumberSchema();
        schema.required().positive().range(1, 100);

        assertTrue(schema.isValid(50)); // valid
        assertFalse(schema.isValid(-10)); // invalid, not positive
        assertFalse(schema.isValid(0)); // invalid, not positive
        assertFalse(schema.isValid(101)); // invalid, above upper bound
    }
}
