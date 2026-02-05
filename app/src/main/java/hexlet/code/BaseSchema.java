package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected abstract boolean isAnyEmpty(T value);
    public final boolean isValid(T value) {
        if (isAnyEmpty(value)) {
            return !checks.containsKey("required");
        }

        return checks.values().stream().allMatch(check -> check.test(value));
    }

    protected final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }
}
