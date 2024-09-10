public class IfDebug {
		public static void main(String argv[]) {
			int score = 91;
			String grade = "";
			
			if(score >= 90) {
				grade = "A";
			}
			else if (score >= 80) {
				grade = "B";
			}
			else if (score >= 70) {
				grade = "C";
			}
			else if (score >= 60) {
				grade = "D";
			}
			else {
				grade = "F";
			}
			System.out.println(grade);
	}
}