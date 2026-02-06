package hexlet.code;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema() {
        super();
    }

    @Override
    protected boolean isAnyEmpty(Map<?, ?> value) {
        return value == null;
    }

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value.size() == size);
        return this;
    }
}
