package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Task2DWAdditionalTests {
    private static DWInteractionGraph testGraph;

    @BeforeAll
    public static void setupTests() {
        testGraph = new DWInteractionGraph("resources/AdditionalTask1&2.txt");
    }

    @Test
    public void testReportActivityInTimeWindow() {
        int[] expected1 = new int[]{5, 6, 9};
        Assertions.assertArrayEquals(expected1, testGraph.ReportActivityInTimeWindow(new int[]{10, 30}));
    }

    @Test
    public void testReportActivityInTimeWindow1() {
        int[] expected2 = new int[]{4, 4, 4};
        Assertions.assertArrayEquals(expected2, testGraph.ReportActivityInTimeWindow(new int[]{53, 53}));
    }

    @Test
    public void testReportActivityInTimeWindow2() {
        int[] expected2 = new int[]{0, 0, 0};
        Assertions.assertArrayEquals(expected2, testGraph.ReportActivityInTimeWindow(new int[]{17, 20}));
    }

    @Test
    public void testReportOnUser() {
        Assertions.assertArrayEquals(new int[]{2, 2, 3}, testGraph.ReportOnUser(20));
    }

    @Test
    public void testReportOnUser1() {
        DWInteractionGraph t = new DWInteractionGraph(testGraph, new int[] {10, 30});
        Assertions.assertArrayEquals(new int[]{2, 1, 2}, t.ReportOnUser(20));
    }

    @Test
    public void testReportOnUser2() {
        Assertions.assertArrayEquals(new int[]{4, 1, 2}, testGraph.ReportOnUser(14));
    }

    @Test
    public void testReportOnUser3() {
        Assertions.assertArrayEquals(new int[]{0, 0, 0}, testGraph.ReportOnUser(6));
    }

    @Test
    public void testNthMostActiveUser() {
        Assertions.assertEquals(14, testGraph.NthMostActiveUser(1, SendOrReceive.SEND));
        Assertions.assertEquals(8, testGraph.NthMostActiveUser(1, SendOrReceive.RECEIVE));
    }

    @Test
    public void testNthMostActiveUser1() {
        Assertions.assertEquals(3, testGraph.NthMostActiveUser(2, SendOrReceive.SEND));
        Assertions.assertEquals(13, testGraph.NthMostActiveUser(2, SendOrReceive.RECEIVE));
    }

    @Test
    public void testNthMostActiveUser2() {
        Assertions.assertEquals(-1, testGraph.NthMostActiveUser(200, SendOrReceive.SEND));
        Assertions.assertEquals(-1, testGraph.NthMostActiveUser(200, SendOrReceive.RECEIVE));
    }

}
