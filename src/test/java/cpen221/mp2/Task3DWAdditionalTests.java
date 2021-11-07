package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Task3DWAdditionalTests {

    private static DWInteractionGraph dwGraph1;
    private static DWInteractionGraph dwGraph2;

    @BeforeAll
    public static void setupTests() {
        dwGraph1 = new DWInteractionGraph("resources/AdditionalTask3DW.txt");
        dwGraph2 = new DWInteractionGraph(dwGraph1, new int[] {30, 32});
    }

    @Test
    public void testBFSGraph1() {
        List<Integer> expected = Arrays.asList(0, 7, 9, 11, 3, 6, 8, 10, 2, 4);
        Assertions.assertEquals(expected, dwGraph1.BFS(0, 4));
    }

    @Test
    public void testBFSGraph2() {
        List<Integer> expected = Arrays.asList(15, 17, 16, 18);
        Assertions.assertEquals(expected, dwGraph2.BFS(15, 18));
    }

    @Test
    public void testBFSGraph3() {
        Assertions.assertEquals(null, dwGraph1.BFS(0, 17));
    }

    @Test
    public void testDFSGraph1() {
        List<Integer> expected = Arrays.asList(0, 7, 3, 2 ,4);
        Assertions.assertEquals(expected, dwGraph1.DFS(0, 4));
    }

    @Test
    public void testDFSGraph2() {
        List<Integer> expected = Arrays.asList(15, 17, 16, 18);
        Assertions.assertEquals(expected, dwGraph2.DFS(15, 18));
    }

    @Test
    public void testDFSGraph3() {
        Assertions.assertEquals(null, dwGraph1.DFS(0, 18));
    }
}
