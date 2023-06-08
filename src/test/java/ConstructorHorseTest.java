import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ConstructorHorseTest {

    @Test
    void constructorNameArgNullTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 0, 0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    //@MethodSource("firstArgsProviderFactory")
    @ValueSource(strings = {"", "   ", "\t  "})
    void constructorNameArgBlankTest(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 0, 0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorSpeedArgTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name", -1, 0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorDistanceArgTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name", 0, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
}