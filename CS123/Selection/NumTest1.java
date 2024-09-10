public class NumTest1 {
		public static void main(String argv[]) {
			int score = 55;
			
			if (score < 0 || score > 100) {
				System.out.println("Score has an illegal value");
			}
			if(score >= 20 && score <= 60) {
				System.out.println("Score is in the range 20-60");
			}
			System.out.println("Done");
	}
}