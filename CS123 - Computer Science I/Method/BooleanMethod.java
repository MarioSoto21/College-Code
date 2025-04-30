public class BooleanMethod {
	public static boolean is_divisible(int x, int y) {
		/*
		if(x % y == 0)
			return true;
		return false;
		*/
		return (x%y == 0);
	}
	public static void main(String[] args ) {
			boolean result = is_divisible(20,5);
			System.out.println(result);

			
	}
}
// Name of boolean method: e.g. is_even, is_empty, is_found, is_equal.