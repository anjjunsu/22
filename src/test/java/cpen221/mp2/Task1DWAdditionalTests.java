package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task1DWAdditionalTests {

    private static DWInteractionGraph baseGraph;
    private static DWInteractionGraph timeFilteredGraph1;
    private static DWInteractionGraph timeFilteredGraph2;
    private static DWInteractionGraph userFilteredGraph1;
    private static DWInteractionGraph userFilteredGraph2;

    @BeforeAll
    public static void setupTests() {
        baseGraph = new DWInteractionGraph("resources/AdditionalTask1&2.txt");

        timeFilteredGraph1 = new DWInteractionGraph(baseGraph, new int[] {10, 30});
        timeFilteredGraph2 = new DWInteractionGraph(baseGraph, new int[] {53, 53});

        List<Integer> userFilter1 = Arrays.asList(13);
        List<Integer> userFilter2 = Arrays.asList(14, 29, 7, 20, 26);
        userFilteredGraph1 = new DWInteractionGraph(baseGraph, userFilter1);
        userFilteredGraph2 = new DWInteractionGraph(baseGraph, userFilter2);
    }

    @Test
    public void testGetUserIdsBaseGraph() {
        Assertions.assertEquals(
            new HashSet<>(Arrays.asList(1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 29, 30)), baseGraph.getUserIDs());
    }

    @Test
    public void testGetUserIdsTimeGraph1() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(14, 8, 22, 13, 15, 20, 26, 29, 21)),
            timeFilteredGraph1.getUserIDs());
    }

    @Test
    public void testGetUserIdsTimeGraph2() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 7, 8, 24, 21, 10, 23, 16)),
            timeFilteredGraph2.getUserIDs());
    }

    @Test
    public void testGetUserIdsUserGraph1() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(13, 15, 30, 10, 18, 21)),
            userFilteredGraph1.getUserIDs());
    }

    @Test
    public void testGetUserIdsUserGraph2() {
        Assertions.assertEquals(
            new HashSet<>(Arrays.asList(14, 8, 16, 29, 21, 7, 3, 1, 18, 20, 26, 22, 30)),
            userFilteredGraph2.getUserIDs());
    }

    @Test
    public void testGetEmailCount() {
        Assertions.assertEquals(1, baseGraph.getEmailCount(20, 26));
        Assertions.assertEquals(1, baseGraph.getEmailCount(26, 20));
    }

    @Test
    public void testGetEmailCount1() {
        Assertions.assertEquals(4, baseGraph.getEmailCount(14, 8));
    }

    @Test
    public void testGetEmailCount2() {
        Assertions.assertEquals(0, baseGraph.getEmailCount(15, 20));
    }

}
