package src.main.mvc;

public class Controller {
    private Model model;
    private View view;

    // Constructor to initialize the model and view
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    // Method to perform sorting and display original and sorted arrays
    public void performSorting() {
        // Get the original array from the model
        int[] originalArray = model.getArray();
        // Display the original array
        view.displayOriginalArray(originalArray);
        
        // Perform QuickSort on the array
        model.performQuickSort();
        
        // Get the sorted array from the model
        int[] sortedArray = model.getArray();
        // Display the sorted array
        view.displaySortedArray(sortedArray);
    }

    // Method to perform binary search and display the result
    public void performSearch(int target) {
        // Perform Binary Search on the array for the target
        int index = model.performBinarySearch(target);
        // Display the search result
        view.displaySearchResult(target, index);
    }
}

