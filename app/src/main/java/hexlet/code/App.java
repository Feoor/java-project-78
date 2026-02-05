package hexlet.code;

public class App {
    public static void checkStringScheme() {
        var v = new Validator();

        var schema = v.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        schema.isValid(""); // true
        schema.isValid(null); // true

        schema.required();

        schema.isValid(null); // false
        schema.isValid(""); // false
        schema.isValid("what does the fox say"); // true
        schema.isValid("hexlet"); // true

        schema.contains("wh").isValid("what does the fox say"); // true
        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); // false

        schema.isValid("what does the fox say"); // false
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

        // Если один валидатор вызывался несколько раз,
        // то последний имеет приоритет (перетирает предыдущий)
        var schema1 = v.string();
        schema1.minLength(10).minLength(4).isValid("Hexlet"); // true
    }

    public static void checkNumberScheme() {
        var v = new Validator();

        var schema = v.number();

        schema.isValid(5); // true

        // Пока не вызван метод required(), null считается валидным
        schema.isValid(null); // true
        schema.positive().isValid(null); // true

        schema.required();

        schema.isValid(null); // false
        schema.isValid(10); // true

        // Потому что ранее мы вызвали метод positive()
        schema.isValid(-10); // false
        //  Ноль — не положительное число
        schema.isValid(0); // false

        schema.range(5, 10);

        schema.isValid(5); // true
        schema.isValid(10); // true
        schema.isValid(4); // false
        schema.isValid(11); // false
    }

    public static void main(String[] args) {
        checkStringScheme();
        checkNumberScheme();
    }
}
