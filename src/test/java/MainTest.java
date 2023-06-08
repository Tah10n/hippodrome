import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class MainTest {

    @Test
    @Timeout(value = 22)
    void mainTest() throws Exception {
        Main.main(null);
    }
}