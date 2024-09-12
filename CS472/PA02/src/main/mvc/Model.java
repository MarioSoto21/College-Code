package src.main.mvc;

import java.util.Arrays;

public class Model {
    private int[] array;

    public Model(int[] array) {
        this.array = array;
    }

    // Method to set the input array
    public void setArray(int[] array) {
        this.array = array;
    }

    // Method to get the input array
    public int[] getArray() {
        return array;
    }

    // Method to perform QuickSort on the input array
    public void performQuickSort() {
        quickSort(0, array.length - 1);
    }

    // QuickSort algorithm implementation
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Method to perform Binary Search on the input array
    public int performBinarySearch(int target) {
        return binarySearch(target, 0, array.length - 1);
    }

    // Binary Search algorithm implementation
    private int binarySearch(int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid; // Target found
            } else if (array[mid] < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return -1; // Target not found
    }

    @Override
    public String toString() {
        return "Model{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
