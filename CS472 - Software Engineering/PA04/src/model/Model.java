package src.model;

import src.search.BinarySearch;
import src.sort.QuickSort;
import java.util.Arrays;

public class Model {
		private String message;
    int[] array;
    //int[] array = {5, 3, 7, 2, 8, 4, 1, 6};

		public String lookup(int key) {
        // Input array
        int n = array.length;

        // Display original array
        System.out.println("Original Array: " + Arrays.toString(array));

        // Sort the array using QuickSort
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array, 0, n - 1);

        // Display sorted array
        System.out.println("Sorted Array: " + Arrays.toString(array));

        // Perform binary search on the sorted array
        BinarySearch binarySearch = new BinarySearch();
        int index = binarySearch.binarySearch(array, key);

        if (index != -1) {
            return "Target found at index: " + Integer.toString(index);
        } else {
            return "Target not found in the array.";
        }
		}

		public boolean saveData(String data) {
        String[] temp = data.split(",");
        array = new int[temp.length];
        int i = 0;

        while(i < temp.length) {
                array[i] = Integer.parseInt(temp[i]);
                i++;
        }

				return true;
		}
}