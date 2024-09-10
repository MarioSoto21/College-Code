// What does the following code print out? Notice how the inner loop
// is started over for each row.
// Can you predict how many rows and colums of starws there will be?
// Can you change the code to be a 10*8 rectangle?

/* public class NestedLoops2 {
	public static void main(String[] args) {
		for(int row = 1; row <= 3; row++) {
			for(int col = 1; col <= 5; col++) {
			System.out.print("*");
			}
			System.out.println();
		}
	}
	
}
*/
public class NestedLoops1 {
	public static void main(String[] args) {
		for(int row = 1; row < 10; row++) {
			for(int col = 1; col < 8; col++) {
			System.out.print("*");
		}
		System.out.println();
		}
	}
}