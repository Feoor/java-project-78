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

    @SuppressWarnings("unchecked")
    public void shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", value -> schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();

                    //noinspection rawtypes
                    BaseSchema schema = entry.getValue();

                    Object dataValue = value.get(key);

                    return schema.isValid(dataValue);
                }));
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
