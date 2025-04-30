public class BreakStatement {
	public static void main(String[] args) {
		int count = 0;
		while (count > 10) {
			System.out.println(count);
			count++;
			if(count == 5)
				break;
		}
		System.out.println("Done!");
	}
}