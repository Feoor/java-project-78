package hexlet.code;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Number> {

    public NumberSchema() {
        super();
    }

    @Override
    protected boolean isAnyEmpty(Number value) {
        return value == null;
    }

    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(Number min, Number max) {
        addCheck("range", value -> value.doubleValue() >= min.doubleValue()
                && value.doubleValue() <= max.doubleValue());
        return this;
    }
}
