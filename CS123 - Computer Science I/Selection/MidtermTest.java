import java.util.Scanner;
public class MidTermTest {
		public static void main(String argv[]) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Did you pass the midterm exam");
			
			boolean passedExam = scan.nextBoolean();
			
			if(passedExam) {
				System.out.println("Good job studying!");
			}
			else {
				System.out.println("Study harder next time! ");
			}
			System.out.println("Good bye!");
	}
}