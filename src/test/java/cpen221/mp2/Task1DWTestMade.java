package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Task1DWTestMade {
    private static DWInteractionGraph testGraphBase;
    private static UDWInteractionGraph testGraph1;
    private static UDWInteractionGraph testGraph2;


    @BeforeAll
    public static void setupTests() {
//        testGraphBase = new UDWInteractionGraph("resources/Task1-2UDWTransactions.txt");
    }

    @Test
    public void testGetUserIds() {
//        testGraph1 = new UDWInteractionGraph(testGraphBase, new int[] {0, 3});
        testGraphBase = new DWInteractionGraph("resources/Task1-2Transactions.txt");

    }

}