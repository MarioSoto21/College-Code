import java.util.Scanner;
public class HeadOrTail {
		public static void main(String argv[]) {
			Scanner scan = new Scanner(System.in);
			
			boolean isHead = scan.nextBoolean();
			
			if(isHead) {
				System.out.println("Let's go to the game");
			}
			else {
				System.out.println("Let's watch a movie");
			}
			System.out.println("After conditional");
	}
}