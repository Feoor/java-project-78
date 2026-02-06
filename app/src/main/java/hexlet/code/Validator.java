package hexlet.code;

public final class Validator {
    public Validator() {
        // Validator is an empty class, so we don't need to do anything in the constructor
    }

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }
}
