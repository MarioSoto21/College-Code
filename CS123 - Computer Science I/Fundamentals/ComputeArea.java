import java.util.Scanner;
public class ComputeArea {
		public static void main(String argv[]) {
			// Create a Scanner object
			Scanner scan = new Scanner(System.in);
			
			// Promt the user to enter a radius
			System.out.println("Please enter a radius");
			double radius = scan.nextDouble();
			// Compute area
			double area = radius * radius * 3.14159;
			
			// Display result
			System.out.println("The area for the circle of radius " + radius
			+ " is " + area);
		}
}