import java.util.Scanner;
public class DriversTest {
		public static void main(String argv[]) {
			Scanner scan = new Scanner(System.in);
			
			int age = scan.nextInt();
			
			if(age >= 16) {
				System.out.println("You can get a drivers license in most states");
			}
			else {
				System.out.println("Sorry, you need to be older to get a drive's license");
			}
		
	}
}