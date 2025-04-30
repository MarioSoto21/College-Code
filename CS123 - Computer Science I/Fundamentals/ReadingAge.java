
import java.util.Scanner;
public class ReadingAge {
		public static void main(String argv[]) {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Please Enter your name");
			// The following statement gets the string value you enter
			// as program input and then stores the value in a variable.
			int age = scan.nextInt();
			System.out.println(age + " years old");
		}
}