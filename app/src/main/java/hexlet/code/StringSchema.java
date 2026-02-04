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

        return this;
    }

    public StringSchema minLength(int length) {

        return this;
    }

    public StringSchema contains(String substring) {

        return this;
    }
}
