import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 25000, 50000, 100000, 150000, 200000};
        for (int size : sizes) {
            double[] array = generateRandomArray(size);
            
            // Merge Sort
            long startTime = System.currentTimeMillis();
            mergeSort(array.clone());
            long mergeSortTime = System.currentTimeMillis() - startTime;
            
            // Quick Sort
            startTime = System.currentTimeMillis();
            quickSort(array.clone(), 0, array.length - 1);
            long quickSortTime = System.currentTimeMillis() - startTime;
            
            // Insertion Sort
            startTime = System.currentTimeMillis();
            insertionSort(array.clone());
            long insertionSortTime = System.currentTimeMillis() - startTime;
            
            System.out.println("Array Size: " + size);
            System.out.println("Merge Sort Time: " + mergeSortTime + " ms");
            System.out.println("Quick Sort Time: " + quickSortTime + " ms");
            System.out.println("Insertion Sort Time: " + insertionSortTime + " ms");
            System.out.println();
        }
    }

    public static double[] generateRandomArray(int size) {
        double[] array = new double[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = 100.00 + (1000.00 - 100.00) * random.nextDouble();
        }
        return array;
    }

    public static void mergeSort(double[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;
            double[] left = Arrays.copyOfRange(array, 0, mid);
            double[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left);
            mergeSort(right);

            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }
            while (i < left.length) {
                array[k++] = left[i++];
            }
            while (j < right.length) {
                array[k++] = right[j++];
            }
        }
    }

    public static void quickSort(double[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(double[] array, int low, int high) {
        double pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        double temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void insertionSort(double[] array) {
        for (int i = 1; i < array.length; i++) {
            double key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
