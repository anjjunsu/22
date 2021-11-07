package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Task4DWAdditionalTests {

    private static DWInteractionGraph dwig1;
    private static DWInteractionGraph dwig2;
    private static DWInteractionGraph dwig3;

    @BeforeAll
    public static void setupTests() {
        dwig1 = new DWInteractionGraph("resources/AdditionalTask4.txt");
        dwig2 = new DWInteractionGraph(dwig1, new int[] {0, 300});
        dwig3 = new DWInteractionGraph(dwig1, new int[] {3600, 60000});
    }

    @Test
    public void testMaxedBreachedUserCount1() {
        Assertions.assertEquals(5, dwig2.MaxBreachedUserCount(1));
    }

    @Test
    public void testMaxedBreachedUserCount2() {
        Assertions.assertEquals(5, dwig3.MaxBreachedUserCount(6));
    }

    @Test
    public void testMaxedBreachedUserCount3() {
        Assertions.assertEquals(8, dwig3.MaxBreachedUserCount(12));
    }

}
