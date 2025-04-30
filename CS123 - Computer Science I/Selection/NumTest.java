public class NumTest {
		public static void main(String argv[]) {
			int score = 110;
			
			if (score < 0 || score > 100) {
				System.out.println("Score has an illegal value");
			}
			 else if(score >= 0 && score <= 100) {
				System.out.println("Score is in the range 0-100");
			}
			System.out.println("Done");
	}
}