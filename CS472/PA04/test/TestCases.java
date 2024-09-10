package test;

import org.junit.Assert;
import org.junit.Test;
import src.controller.Controller;
import src.model.Model;
import src.search.BinarySearch;
import src.sort.QuickSort;
import src.view.View;
import src.main.app.Runner;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TestCases {

    // BinarySearch Tests
    @Test
    public void testBinarySearchWithTargetPresent() {
        // Verifies that the binary search algorithm correctly finds the index of a target present in a sorted array.
        BinarySearch binarySearch = new BinarySearch();
        int[] array = {1, 2, 3, 4, 5};
        int target = 3;
        Assert.assertEquals(2, binarySearch.binarySearch(array, target));
    }

    @Test
    public void testBinarySearchWithTargetNotPresent() {
        // Ensures that the binary search algorithm returns -1 when the target is not present in the array.
        BinarySearch binarySearch = new BinarySearch();
        int[] array = {1, 2, 3, 4, 5};
        int target = 6;
        Assert.assertEquals(-1, binarySearch.binarySearch(array, target));
    }

    @Test
    public void testBinarySearchWithEmptyArray() {
        // Tests the binary search algorithm with an empty array, ensuring it returns -1.
        BinarySearch binarySearch = new BinarySearch();
        int[] array = {};
        int target = 5;
        Assert.assertEquals(-1, binarySearch.binarySearch(array, target));
    }

    @Test
    public void testBinarySearchWithNumberRight() {
        // Tests binary search with a target located at the rightmost position in the array.
        BinarySearch binarySearch = new BinarySearch();
        int[] array = {1, 3, 5, 7, 9};
        int target = 9;
        Assert.assertEquals(4, binarySearch.binarySearch(array, target));
    }

    @Test
    public void testBinarySearchWithNumberLeft() {
        // Tests binary search with a target located at the leftmost position in the array.
        BinarySearch binarySearch = new BinarySearch();
        int[] array = {1, 3, 5, 7, 9};
        int target = 1;
        Assert.assertEquals(0, binarySearch.binarySearch(array, target));
    }

    // QuickSort Tests
    @Test
    public void testQuickSortWithSortedArray() {
        // Verifies that the QuickSort algorithm correctly sorts an already sorted array.
        QuickSort quickSort = new QuickSort();
        int[] sortedArray = {1, 2, 3, 4, 5};
        quickSort.quickSort(sortedArray, 0, sortedArray.length - 1);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedArray);
    }

    @Test
    public void testQuickSortWithUnsortedArray() {
        // Tests QuickSort with an unsorted array, ensuring it sorts the array correctly.
        QuickSort quickSort = new QuickSort();
        int[] unsortedArray = {5, 4, 3, 2, 1};
        quickSort.quickSort(unsortedArray, 0, unsortedArray.length - 1);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, unsortedArray);
    }

    @Test
    public void testQuickSortWithEmptyArray() {
        // Tests QuickSort with an empty array, ensuring it doesn't throw any exceptions.
        QuickSort quickSort = new QuickSort();
        int[] emptyArray = {};
        quickSort.quickSort(emptyArray, 0, emptyArray.length - 1);
        Assert.assertArrayEquals(new int[]{}, emptyArray);
    }

    // View Test
    @Test
    public void testView() {
        // Tests the View class by simulating user input and verifying the output.
        String input = "1,2,3,4,5\n3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Model model = new Model();
        Controller controller = new Controller(model, new View("TestView"));
        View view = new View("TestView");
        view.setController(controller);
        view.sendMessage();

        String expectedOutput = "Enter a string of digits and split them with comma: \n" +
                "Enter the number you're looking for: \n" +
                "TestView : Your data (1,2,3,4,5) has been successfully delivered.\n" +
                "Target found at index: 2\n" +
                "Enter the number you're looking for: \n";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }

    // Model Tests
    @Test
    public void testModelLookup() {
        // Tests the Model class by verifying its lookup method's behavior with different inputs.
        Model model = new Model();
        int[] array = {1, 2, 3, 4, 5};
        model.saveData("1,2,3,4,5");
        Assert.assertEquals("Target found at index: 2", model.lookup(3));
        Assert.assertEquals("Target not found in the array.", model.lookup(6));
    }

  
}
