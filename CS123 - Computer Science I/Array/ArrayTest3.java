public class ArrayTest3 {
	public static void main(String[] args) {
		
		int[] highScores = new int[6];

		System.out.println(highScores[0]);
		
		highScores[0] = 99;
		highScores[1] = 98;
		highScores[2] = 98;
		highScores[3] = 88;
		highScores[4] = 68;
		highScores[5] = 58;
		
		highScores[5] = 97;
		
		System.out.println(highScores[2]);
		System.out.println(highScores[5]);
		
		System.out.println(highScores[6]);
		
	}
}
