package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Task4DWAdditionalTests1 {

    private static DWInteractionGraph dwig1;
    private static DWInteractionGraph dwig2;
    private static DWInteractionGraph dwig3;
    private static DWInteractionGraph dwig4;


    @BeforeAll
    public static void setupTests() {
        dwig1 = new DWInteractionGraph("resources/AdditionalTask4.txt");
        dwig2 = new DWInteractionGraph(dwig1, new int[] {0, 300});
        dwig3 = new DWInteractionGraph(dwig1, new int[] {3600, 60000});
        dwig4 = new DWInteractionGraph("resources/Task4Additional.txt");

    }

    @Test
    public void testMaxedBreachedUserCount0() {
        // Attacking user 7 any time in the window [0,120] will pollute 8 users in a 2 hour window.
        Assertions.assertEquals(7, dwig4.MaxBreachedUserCount(2));
    }

    @Test
    public void testMaxedBreachedUserCount1() {
        Assertions.assertEquals(5, dwig2.MaxBreachedUserCount(1));
    }

    @Test
    public void testMaxedBreachedUserCount2() {
        Assertions.assertEquals(6, dwig3.MaxBreachedUserCount(6));
    }

    @Test
    public void testMaxedBreachedUserCount3() {
        Assertions.assertEquals(12, dwig3.MaxBreachedUserCount(12));
    }

}