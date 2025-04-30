public class UpdateScore2 {
		public static void main(String argv[]) {
	int x = .8;
			if(x < .25) {
				System.out.println("first quartile");
			}
			else if (x < .5) {
				System.out.println("second quartile");
			else if (x < .75) {
				System.out.prinln("third quartile");
			}
			else {
				System.out.println("fourth quartile");
			}
	}
}