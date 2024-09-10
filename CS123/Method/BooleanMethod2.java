public class BooleanMethod2 {
	public static boolean is_between(int x, int y, int z) {
		
		if(x <= y)
			if(y <= z)
				return true;
		
			
		return false;
		
		
	}
	public static void main(String[] args ) {
			boolean result = is_between(10, 10, 15);
			System.out.println(result);
	}
}
