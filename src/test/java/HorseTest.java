import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.suite.api.*;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    Horse horse;
//
    static Stream<String> nameProviderFactory() {
        return Stream.of("alex", "brian",  "charles");
    }
    static Stream<Double> speedProviderFactory() {
        return Stream.of(10.0,20.0,30.0);
    }
    static Stream<Double> distanceProviderFactory() {
        return Stream.of(0.0,1.0,2.0);
    }

    @ParameterizedTest
    @MethodSource("nameProviderFactory")
    void getName(String name) {
        horse = new Horse(name, 0, 0);
        assertEquals(name, horse.getName());

    }

    @ParameterizedTest
    @MethodSource("speedProviderFactory")
    void getSpeed(double speed) {
        horse = new Horse("speeding", speed, 0);
        assertEquals(speed, horse.getSpeed());
    }

    @ParameterizedTest
    @MethodSource("distanceProviderFactory")
    void getDistance(double distance) {
        horse = new Horse("distancing", 2, distance);
        assertEquals(distance, horse.getDistance());

    }

    @Test
    void getZeroDistance() {
        Horse horse2 = new Horse("zeroDistance",2.0);
        assertEquals(0,horse2.getDistance());
    }

    @ParameterizedTest
    @CsvSource({
            "1,0,1",
            "2,1,3",
            "3,4,7"
    })
    void move(double speed, double distance, double random) {
        try(MockedStatic<Horse> randomDouble = Mockito.mockStatic(Horse.class)) {

            horse = new Horse("moving", speed, distance);
            horse.move();
            randomDouble.verify(()-> Horse.getRandomDouble(0.2,0.9));

            randomDouble.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            horse.move();
            assertEquals(distance + speed*random,horse.getDistance());
        }
    }
}