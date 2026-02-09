package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        super();
    }

    @Override
    protected boolean isAnyEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public StringSchema required() {
        addCheck("required", value -> !isAnyEmpty(value));
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> value.contains(substring));
        return this;
    }
}
