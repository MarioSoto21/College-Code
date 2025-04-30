
public class UpdateScore {
	public static void main(String argv[]) {
		int score = 0;
		System.out.println(score); // 0
		score = score + 1;
		System.out.println(score); // 1
		score = score + 1;
		System.out.println(score); // 2
		score++; // score = score+1
		System.out.println(score); // 3
		score+=2;
		System.out.println(score); // 5
	}
}