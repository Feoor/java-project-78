package hexlet.code;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void testApp() {
        App.main(null);
        App.checkStringScheme();
//        App.checkNumberScheme();
        App.checkMapScheme();
    }
}
