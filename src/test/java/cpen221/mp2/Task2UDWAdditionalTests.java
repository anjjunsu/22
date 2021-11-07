package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Task2UDWAdditionalTests {
    private static UDWInteractionGraph testGraph;

    @BeforeAll
    public static void setupTests() {
        testGraph = new UDWInteractionGraph("resources/AdditionalTask1&2.txt");
    }

    @Test
    public void testReportActivityInTimeWindow() {
        int[] result = testGraph.ReportActivityInTimeWindow(new int[]{10, 30});
        Assertions.assertEquals(9, result[0]);
        Assertions.assertEquals(9, result[1]);
    }

    @Test
    public void testReportActivityInTimeWindow1() {
        int[] result = testGraph.ReportActivityInTimeWindow(new int[]{53, 53});
        Assertions.assertEquals(8, result[0]);
        Assertions.assertEquals(4, result[1]);
    }

    @Test
    public void testReportActivityInTimeWindow2() {
        int[] result = testGraph.ReportActivityInTimeWindow(new int[]{17, 20});
        Assertions.assertEquals(0, result[0]);
        Assertions.assertEquals(0, result[1]);
    }

    @Test
    public void testReportOnUser() {
        int[] result = testGraph.ReportOnUser(20);
        Assertions.assertEquals(4, result[0]);
        Assertions.assertEquals(3, result[1]);
    }

    @Test
    public void testReportOnUser1() {
        UDWInteractionGraph t = new UDWInteractionGraph(testGraph, new int[] {10, 30});
        int[] result = t.ReportOnUser(20);
        Assertions.assertEquals(3, result[0]);
        Assertions.assertEquals(2, result[1]);
    }

    @Test
    public void testReportOnUser2() {
        int[] result = testGraph.ReportOnUser(6);
        Assertions.assertEquals(0, result[0]);
        Assertions.assertEquals(0, result[1]);
    }

    @Test
    public void testNthActiveUser() {
        Assertions.assertEquals(8, testGraph.NthMostActiveUser(1));
    }

    @Test
    public void testNthActiveUser1() {
        UDWInteractionGraph t = new UDWInteractionGraph(testGraph, new int[] {16, 16});
        Assertions.assertEquals(8, t.NthMostActiveUser(1));
    }

    @Test
    public void testNthActiveUser2() {
        Assertions.assertEquals(20, testGraph.NthMostActiveUser(2));
    }

    @Test
    public void testNthActiveUser3() {
        Assertions.assertEquals(3, testGraph.NthMostActiveUser(3));
    }

    @Test
    public void testNthActiveUser4() {
        Assertions.assertEquals(-1, testGraph.NthMostActiveUser(20));
    }
}
