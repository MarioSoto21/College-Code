package src.sorting;
public class QuickSort {
	public void quickSort(final int[] array, final int low, final int high) {
		if (low < high) {
			int pivotIndex = partition(array, low, high);
			quickSort(array, low, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, high);
		}
	}
	private int partition(final int[] array, final int low, final int high) {
		int pivot = array[high];
		int i = low - 1;
		
		for (int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, high);
		return i + 1;
	}
	private void swap(final int[] array, final int i, final int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
