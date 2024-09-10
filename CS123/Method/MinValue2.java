public class MinValue2 {
	public static int find_min(int x, int y) {
		if(x>y){
			return 1;
		}
		else if(x == y) {
			return 0;
		}
		else {
			return -1;
		}		
	}
	public static void main(String[] args ) {
			int value = find_min(-10, -10);
			System.out.println(value);
	}
}