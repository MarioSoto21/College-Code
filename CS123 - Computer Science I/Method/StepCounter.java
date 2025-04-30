public class StepCounter {
		
	
	public static double convertToMiles(double numSteps) {
		double numMiles = (double) numSteps/2000;
		return numMiles;
	}
		public static void main(String[] args ) {
			System.out.println("500 steps is equal to " + convertToMiles(500) + " miles");
			System.out.println("2000 steps is equal to " + convertToMiles(2000) + " miles");
			System.out.println("3000 steps is equal to " + convertToMiles(3000) + " miles");
	}
}