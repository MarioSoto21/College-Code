import java.util.Scanner;
public class ColdTest {
		public static void main(String argv[]) {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("is it cold?");
			boolean isCold = scan.nextBoolean();
			
			if(isCold == true){
				System.out.println("Wear a coat");
				System.out.println("Wear gloves");
			}
			
	}
}