// This program shows an wxample of promting the user to enter a 
// name and then pringing a greeting.
import java.util.Scanner;
public class ReadingName {
		public static void main(String argv[]) {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Please Enter your name");
			// The following statement gets the string value you enter
			// as program input and then stores the value in a variable.
			String name = scan.nextLine();
			System.out.println("Hello " + name);
		}
}