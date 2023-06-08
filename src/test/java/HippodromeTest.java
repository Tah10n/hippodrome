import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HippodromeTest {
    static Hippodrome hippodrome;
    static List<Horse> horses;


    @BeforeAll
    public static void createHorses() {
        horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("name" + i, i, i);

            horses.add(horse);
        }
        hippodrome = new Hippodrome(horses);
    }


    @Order(1)
    @Test
    void getHorses() {
        assertEquals(horses, hippodrome.getHorses());
    }

    @Order(3)
    @Test
    void move() {
        horses.clear();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Order(2)
    @Test
    void getWinner() {
        Horse last = horses.get(horses.size() - 1);
        assertEquals(last, hippodrome.getWinner());
    }
}