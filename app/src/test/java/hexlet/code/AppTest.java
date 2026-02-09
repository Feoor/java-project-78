package hexlet.code;

import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void testApp() {
        App.main(null);
        App.checkStringScheme();
//        App.checkNumberScheme();
        App.checkMapScheme();
    }
}
