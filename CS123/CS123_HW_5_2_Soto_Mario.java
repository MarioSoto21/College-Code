public class CS123_HW_5_2_Soto_Mario {
			 
	
	public static int largestOfFour(int a, int b, int c, int d) {
		if(a<=b && b<=c && c<= d){
			return a;
		}
		else if(b<=a && a<=c && c<= d){
			return b;
		}
		else if(c<=a && a<=b && b<= d){
			return c;
		}
		else{
			return d;
		}
	}
	
		public static void main(String[] args ) {
			System.out.println(largestOfFour(15, 15, 20, 10));

	}
}