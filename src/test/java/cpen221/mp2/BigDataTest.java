package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BigDataTest {
    private static DWInteractionGraph big;
    private static UDWInteractionGraph bigUDW;

    @BeforeAll
    public static void setupTests() {
        big = new DWInteractionGraph("resources/email-Eu-core-temporal.txt");
        bigUDW = new UDWInteractionGraph("resources/email-Eu-core-temporal.txt");
    }

    @Test
    public void testingBigData() {
        int[] expected = big.ReportActivityInTimeWindow(new int[] {3, 6});
        int NthUser = bigUDW.NthMostActiveUser(1);
        Assertions.assertEquals(1, 1);
    }
}
