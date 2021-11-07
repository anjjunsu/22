package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Junsu_Test {

    private static DWInteractionGraph selfEmailing;
    private static DWInteractionGraph noInteractionsAtAll;
    private static DWInteractionGraph weirdFormattedFile;
    private static DWInteractionGraph empty;

    @BeforeAll
    public static void setupTests() {
        selfEmailing = new DWInteractionGraph("resources/Junsu_Task3_emailToMyself.txt");
        noInteractionsAtAll = new DWInteractionGraph("resources/noInteractionsAtAll.txt");
//        weirdFormattedFile = new DWInteractionGraph("resources/weirdFormat.txt");
        empty = new DWInteractionGraph("resources/empty.txt");
    }

    // get Email count. What if there's no weight between sender and receiver?
    // What if no interaction at all? will graph be made?
    @Test
    public void noInteraction() {
        Assertions.assertEquals(0, selfEmailing.getEmailCount(0,1));
        Assertions.assertEquals(0, noInteractionsAtAll.getEmailCount(0, 3));
    }

    @Test
    public void DFS_noInteractions() {
        Assertions.assertNull(noInteractionsAtAll.DFS(0, 4));
        Assertions.assertNull(selfEmailing.DFS(0, 1));
    }

    @Test
    public void BFS_noPathExist() {
        Assertions.assertNull(noInteractionsAtAll.BFS(1, 3));
        Assertions.assertNull(selfEmailing.BFS(0, 1));
    }
    // Task1 what if
    // there are more than one spacing between number in raw data
    // there are spaces before first character?

//    @Test
//    public void testWeirdFormattedFile() {
//        Assertions.assertArrayEquals(new int[]{1, 2, 4}, weirdFormattedFile.ReportOnUser(2));
//    }


    // What if all the users are filtered out?

    // In task2 time filter thing, what if time span is 0? like [11,11]

    // In task 3 DFS, what if there is no path?

    // In task 3 for both DW and UDW, what if input user id is not valid?

    // Task 3, what if user sent an email to user itself?
    @Test
    public void testPathSameFrom_To() {
        List<Integer> expected = Arrays.asList(0);
        Assertions.assertEquals(expected, selfEmailing.DFS(0, 0));
        List<Integer> expected1 = Arrays.asList(1);
        Assertions.assertEquals(expected1, selfEmailing.DFS(1, 1));
        // No path between user 0 and 1
        Assertions.assertNull(selfEmailing.DFS(0, 1));
    }

    @Test
    public void twoPossiblePaths() {
        List<Integer> expected = Arrays.asList(1);
        Assertions.assertEquals(expected, selfEmailing.DFS(1, 1));
    }
    // Task3 DFS, what if there are too many cycles?

    // Task 4, what if the input data are not ordered in increasing order?
}
