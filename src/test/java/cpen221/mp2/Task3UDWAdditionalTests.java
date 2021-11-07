package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Task3UDWAdditionalTests {

    private static UDWInteractionGraph udwGraph1;
    private static UDWInteractionGraph udwGraph2;

    @BeforeAll
    public static void setupTests() {
        udwGraph1 = new UDWInteractionGraph("resources/AdditionalTask3UDW.txt");
        udwGraph2 = new UDWInteractionGraph(udwGraph1, new int[] {1, 1});
    }

    @Test
    public void testNumComponent() {
        Assertions.assertEquals(4, udwGraph1.NumberOfComponents());
    }

    @Test
    public void testNoComponent() {
        Assertions.assertEquals(0, udwGraph2.NumberOfComponents());
    }

    @Test
    public void testPathExists() {
        Assertions.assertTrue(udwGraph1.PathExists(0, 2));
        Assertions.assertTrue(udwGraph1.PathExists(4, 3));
        Assertions.assertTrue(udwGraph1.PathExists(5, 4));
        Assertions.assertFalse(udwGraph1.PathExists(3, 7));
        Assertions.assertFalse(udwGraph1.PathExists(5, 9));
        Assertions.assertTrue(udwGraph1.PathExists(10, 9));
        Assertions.assertTrue(udwGraph1.PathExists(11, 11));
        Assertions.assertFalse(udwGraph1.PathExists(11, 9));
        Assertions.assertFalse(udwGraph1.PathExists(9, 6));
        Assertions.assertFalse(udwGraph1.PathExists(11, 8));
        Assertions.assertFalse(udwGraph1.PathExists(3, 11));
    }

}
