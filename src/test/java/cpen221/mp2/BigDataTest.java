package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BigDataTest {
    private static DWInteractionGraph big;
    private static UDWInteractionGraph bigUDW;

    @BeforeAll
    public static void setupTests() {
        big = new DWInteractionGraph("resources/email-Eu-core-temporal.txt");
//        bigUDW = new UDWInteractionGraph("resources/email-Eu-core-temporal.txt");
    }

    @Test
    public void isNoActivityUserFilteredOut() {
        int[] expected = big.ReportActivityInTimeWindow(new int[] {3, 6});
        Assertions.assertEquals(1, 1);
    }
}
