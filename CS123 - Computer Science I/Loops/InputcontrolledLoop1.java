import java.util.Scanner;
public class InputcontrolledLoop1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("What is on your mind?");
		
		String input = in.nextLine();
		
		
		while (!input.equals("Bye")) {
			// provide a random response
			if(Math.random() < .05) {
				System.out.println("How interesting!");
			}
			else {
			System.out.println("Tell me more!");
			}
		
			input = in.nextLine();
		}
		System.out.println("Done");
	}
}
 