package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Number> {

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
        if (min.doubleValue() > max.doubleValue()) {
            throw new IllegalArgumentException("Min value must be less than max value");
        }

        addCheck("range", value -> value.doubleValue() >= min.doubleValue()
                && value.doubleValue() <= max.doubleValue());
        return this;
    }
}
