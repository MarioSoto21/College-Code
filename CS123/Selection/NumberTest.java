
public class NumberTest {
		public static void main(String argv[]) {
			// Get a random nuber from -10 to 10
			int number = (int)(Math.random()*21 - 10);
			System.out.println("The number is " + number);
			if (number > 0) {
				System.out.println(number + " is positive");
			}
			if (number == 0) {
				System.out.println(number + " is zero");
			}
			if (number < 0) {
				System.out.println(number + " is negative");
			}
	}
}