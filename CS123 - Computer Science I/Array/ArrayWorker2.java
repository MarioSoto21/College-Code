public class ArrayWorker2 {
	/** Double the first 5 elements of the array */
	public static void doubleLastHalf(int[] values) {
		for(int i = values.length/2; i < values.length && i < 5; i++){
			values[i] = values[i] * 2;
		}
	}
	public static void printArray(int[] values){
		for(int i = 0; i < values.length; i++){
			System.out.println(values[i]);
		}
	}
	
		public static void main(String[] args) {
		int[] numArray = {-3, 8, -3, 2};
		int[] oddArray = {-3, 8, -3, 2, -2};
		doubleLastHalf(numArray);
		printArray(numArray);
		doubleLastHalf(oddArray);
		printArray(oddArray);
		
	}
}
