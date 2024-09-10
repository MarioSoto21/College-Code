// The inchesToCentimeters method defines a local variable centimeters, which
// is only visible inside that method. The main method can't see o use the variable.
// Each time the inchesTocentimetes metho is called, a new memory location is 
// created for the local variable. The formal parameter is only visible in the 
// method body.
public class ScopeExample {
		
	
	public static void inchesToCentimeters(double inches) {
		double centimeters = inches * 2.54;
		System.out.println(inches + "-->" + centimeters);
	}
		
		public static void main(String[] args ) {
			inchesToCentimeters(10);
			inchesToCentimeters(15.7);
			calculatePay("inches is: " + inches);

	}
}