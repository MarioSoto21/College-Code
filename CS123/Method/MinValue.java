public class MinValue {
	public static int find_min(int x, int y, int z) {
		if(x<y){
			if(x>z)
				return z;
			else 
				return x;
		}
		else {
			if(y<z)
				return y;
			else 
				return z; 
			
	}
	}
	public static void main(String[] args ) {
			int value = find_min(-52, -20, 30);
			System.out.println(value);
	}
}