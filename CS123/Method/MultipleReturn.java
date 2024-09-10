public class MultipleReturn {
		
	
	public static double absolute_value(int x) {
		if(x<0)
			return -x;
		else
			return x;
	}
		public static void main(String[] args ) {
			System.out.println(absolute_value(-50));
	}
}