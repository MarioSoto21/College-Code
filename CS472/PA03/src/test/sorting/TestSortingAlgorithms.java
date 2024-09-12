package test.sorting;

import org.junit.Test;
import static org.junit.Assert.*;
import src.main.sorting.QuickSort;
import src.main.sorting.BinarySearch;

public class TestSortingAlgorithms {

    @Test
    public void testQuickSort() {
        // Test case 1: Test sorting of an unsorted array
        int[] array1 = {5, 3, 7, 2, 8, 4, 1, 6};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array1, 0, array1.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, array1);

        // Test case 2: Test sorting of an already sorted array
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8};
        quickSort.quickSort(array2, 0, array2.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, array2);
    }

    @Test
    public void testBinarySearch() {
        // Test case 1: Target found in the middle of the array
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8};
        BinarySearch binarySearch = new BinarySearch();
        assertEquals(4, binarySearch.binarySearch(array1, 5));

        // Test case 2: Target not found in the array
        assertEquals(-1, binarySearch.binarySearch(array1, 9));
    }
}
