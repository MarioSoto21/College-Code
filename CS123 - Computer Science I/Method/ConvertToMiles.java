public class ConvertToMiles {
		
	
	public static double convertToMiles(double inches) {
		double centimeters = inches * 2.54;
		return centimeters;
	}
		public static void main(String[] args ) {
			System.out.println(inchesToCentimeters(10));
			System.out.println(inchesToCentimeters(12));
			

	}
}