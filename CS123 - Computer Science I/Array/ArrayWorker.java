 public class ArrayWorker {
	/** Double the first 5 elements of the array */
	public static void doubleFirstFive(int[] values) {
		for(int i = 0; i < values.length && i < 5; i++){
			values[i] = values[i] * 2;
		}
	}
	
	public static void tripleFirstFour(int[] values){
		for(int i = 0; i < values.length && i < 4; i++){
			values[i] = values[i] * 3;
		}
	}
	
	public static void printArray(int[] values){
		for(int i = 0; i < values.length; i++){
			System.out.println(values[i]);
		}
	}
	
		public static void main(String[] args) {
		int[] numArray = {-3, 8, -3, 2, 20, 5, 33, 1};
		doubleFirstFive(numArray);
		/** tripleFirstFour(numArray); */
		printArray(numArray);
		
	}
}
