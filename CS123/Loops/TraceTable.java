public class TraceTable {
	public static void main(String argv[]) {
		int turns = 0;
		int x = 3;
		
		while (turns < 13) { 
			x = x * 3;
			turns = turns + 3; 
		}
		System.out.println("x is " + x);
		System.out.println("turns is " + turns);
	}
}