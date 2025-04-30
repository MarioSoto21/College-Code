package src.main.mvc;
import java.util.Arrays;

public class View {
    // Method to display the original array
    public void displayOriginalArray(int[] array) {
        System.out.println("Original Array: " + Arrays.toString(array));
    }

    // Method to display the sorted array
    public void displaySortedArray(int[] array) {
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    // Method to display the search result
    public void displaySearchResult(int target, int index) {
        if (index != -1) {
            System.out.println("Target " + target + " found at index: " + index);
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }
}


