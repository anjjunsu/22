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
    private static UDWInteractionGraph udwudw;
    private static DWInteractionGraph timeFiltered;


    @BeforeAll
    public static void setupTests() {
        selfEmailing = new DWInteractionGraph("resources/Junsu_Task3_emailToMyself.txt");
        noInteractionsAtAll = new DWInteractionGraph("resources/noInteractionsAtAll.txt");
//        weirdFormattedFile = new DWInteractionGraph("resources/weirdFormat.txt");
        empty = new DWInteractionGraph("resources/empty.txt");
        udwudw = new UDWInteractionGraph("resources/Junsu_UDW_Test.txt");
        timeFiltered = new DWInteractionGraph("resources/Junsu_DWI_NthMost_TimeFilter.txt");
    }

    //Task2 [NthMostActive]: Test 0 activity user filtered out
    @Test
    public void isNoActivityUserFilteredOut() {
        Assertions.assertEquals(-1, selfEmailing.NthMostActiveUser(4, SendOrReceive.SEND));
        Assertions.assertEquals(3, selfEmailing.NthMostActiveUser(2, SendOrReceive.RECEIVE));
    }

   // Test cases where interactions do not exist at all
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

    //Test if input user is not exist in DFS and BFS
    @Test
    public void noUserExists() {
        Assertions.assertNull(noInteractionsAtAll.DFS(11, 55));
        Assertions.assertNull(noInteractionsAtAll.BFS(23, 99));
    }


    // Task1 what if
    // there are more than one spacing between number in raw data
    // there are spaces before first character?

//    @Test
//    public void testWeirdFormattedFile() {
//        Assertions.assertArrayEquals(new int[]{1, 2, 4}, weirdFormattedFile.ReportOnUser(2));
//    }

    @Test
    public void testEmptyGraph() {
        int[] expected1 = {0, 0, 0};
        Assertions.assertArrayEquals(expected1, empty.ReportOnUser(0));
        Assertions.assertArrayEquals(expected1, empty.ReportActivityInTimeWindow(new int[]{0, 1000}));
        Assertions.assertEquals(-1, empty.NthMostActiveUser(1, SendOrReceive.SEND));
    }

    // Test NthMostActiveUser on time filtered DWI
    @Test
    public void timeFilteredDWI() {
        DWInteractionGraph DUT = new DWInteractionGraph(timeFiltered, new int[] {13, 50});
        Assertions.assertEquals(0, DUT.NthMostActiveUser(1, SendOrReceive.SEND));
        Assertions.assertEquals(-1, DUT.NthMostActiveUser(5, SendOrReceive.SEND));
        Assertions.assertEquals(2, DUT.NthMostActiveUser(2, SendOrReceive.RECEIVE));
        Assertions.assertEquals(4, DUT.NthMostActiveUser(5, SendOrReceive.RECEIVE));
        Assertions.assertEquals(-1, DUT.NthMostActiveUser(6, SendOrReceive.RECEIVE));
    }

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
    }

    @Test
    public void twoPossiblePaths() {
        List<Integer> expected = Arrays.asList(1);
        Assertions.assertEquals(expected, selfEmailing.DFS(1, 1));
    }
    // Task3 DFS, what if there are too many cycles?

    // Task 4, what if the input data are not ordered in increasing order?

    //=========================UDW TESTS==================================//
    @Test
    public void testUDW_NthMostActive() {
        Assertions.assertEquals(0, udwudw.NthMostActiveUser(1));
        Assertions.assertEquals(3, udwudw.NthMostActiveUser(2));
        Assertions.assertEquals(1, udwudw.NthMostActiveUser(3));
        Assertions.assertEquals(2, udwudw.NthMostActiveUser(4));
        Assertions.assertEquals(10, udwudw.NthMostActiveUser(5));
        Assertions.assertEquals(4, udwudw.NthMostActiveUser(6));
        Assertions.assertEquals(6, udwudw.NthMostActiveUser(7));
        Assertions.assertEquals(-1, udwudw.NthMostActiveUser(8));
    }
}
