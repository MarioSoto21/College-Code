public class EvenOdd {
		public static void main(String argv[]) {
			int age1 = 15;
			int age2 = 16;
			int divisor = 2;
			
			System.out.println("Remainder of " + age1 + "/" + " is " + (age1 % divisor));
			System.out.println("Remainder of " + age2 + "/" + " is " + (age2 % divisor));
			System.out.println("Is " + age1 + " even?" + (age1 % 2 == 0));
			System.out.println("Is " + age2 + " even?" + (age2 % 2 == 0));


		}
}