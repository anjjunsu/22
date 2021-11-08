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
    private static DWInteractionGraph dwig4;
    private static DWInteractionGraph dwig5;
    private static DWInteractionGraph dwig6;
    private static DWInteractionGraph dwig7;

    @BeforeAll
    public static void setupTests() {
        dwig1 = new DWInteractionGraph("resources/AdditionalTask4.txt");
        dwig2 = new DWInteractionGraph(dwig1, new int[] {0, 300});
        dwig3 = new DWInteractionGraph(dwig1, new int[] {3600, 60000});
        dwig4 = new DWInteractionGraph(dwig1, new int[] {70000, 132000});
        dwig5 = new DWInteractionGraph(dwig1, new int[] {200000, 202000});
        dwig6 = new DWInteractionGraph(dwig1, new int[] {300000, 301500});
        dwig7 = new DWInteractionGraph(dwig1, new int[] {400000, 402000});
    }

    @Test
    public void testMaxedBreachedUserCount1() {
        Assertions.assertEquals(5, dwig2.MaxBreachedUserCount(1));
    }

    @Test
    public void testMaxedBreachedUserCount2() {
        Assertions.assertEquals(5, dwig3.MaxBreachedUserCount(1));
    }

    @Test
    public void testMaxedBreachedUserCount3() {
        Assertions.assertEquals(8, dwig3.MaxBreachedUserCount(12));
    }

    @Test
    public void testMaxedBreachedUserCount4() {
        Assertions.assertEquals(3, dwig4.MaxBreachedUserCount(1));
    }

    @Test
    public void testMaxedBreachedUserCount5() {
        Assertions.assertEquals(12, dwig4.MaxBreachedUserCount(18));
    }

    @Test
    public void testMaxedBreachedUserCount6() {
        Assertions.assertEquals(4, dwig5.MaxBreachedUserCount(1));
    }

    @Test
    public void testMaxedBreachedUserCount7() {
        Assertions.assertEquals(2, dwig6.MaxBreachedUserCount(1));
    }

    @Test
    public void testMaxedBreachedUserCount8() {
        Assertions.assertEquals(3, dwig7.MaxBreachedUserCount(1));
    }

}
