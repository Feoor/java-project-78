package hexlet.code;

public class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        super();
    }

    @Override
    protected boolean isAnyEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public StringSchema required() {
        checks.put("required", value -> !isAnyEmpty(value));
        return this;
    }

    public StringSchema minLength(int length) {
        checks.put("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.put("contains", value -> value.contains(substring));
        return this;
    }
}