// What does the following code print out?
// Run the code to see if you are right.
// Add Another method that finds the index of the last element greater than the
// target indead of smaller than the target
 class ArrayFindSmallest {
	/** return index of the last number smaller than target */
	public static int getIndexOfLastElementSmallerThanTarget(int[] values, int target) {
		for(int index = values.length - 1; index >= 0; index--){
			if(values[index] < target)
				return index;
		}
		return -1;
	}
	/** Add a method called getIndexOfLastElementGreaterThanTarget
		param int array
		param int target
		return index of the last number greater than target
	*/
	public static int getIndexOfLastElementGreaterThanTarget(int[] values, int target){
		for( int index = values.length-1; index >= 0; index--){
			if(values[index] > target)
				return index;
		}
		return -1;
	}
		public static void main(String[] args) {
		int[] theArray = {-30, -5, 8, 23, 46};
		System.out.println(" Last index of element smaller than 50: " +
		getIndexOfLastElementSmallerThanTarget(theArray, 50));
		System.out.println(" Last index of element smaller than 30: " +
		getIndexOfLastElementSmallerThanTarget(theArray, 30));
		System.out.println(" Last index of element smaller than 10: " +
		getIndexOfLastElementSmallerThanTarget(theArray, 10));
		System.out.println(" Last index of element greater than 30: " +
		getIndexOfLastElementGreaterThanTarget(theArray, 30));
		System.out.println(" Last index of element greater than 10: " +
		getIndexOfLastElementGreaterThanTarget(theArray, 10));
		System.out.println(" Last index of element greater than 50: " +
		getIndexOfLastElementGreaterThanTarget(theArray, 50));
	}
}
