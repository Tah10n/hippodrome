import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstructorHippodromeTest {
    @Test
    void constructorArgNullTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorArgBlankTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(new ArrayList<>());
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
}
