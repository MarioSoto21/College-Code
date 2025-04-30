public class CS123_HW_2_SotoMario {
		public static void main(String argv[]) {
			double total = 12.45;
			int dollars = (int)total;
			int totalInt = (int)(total * 100);
			int cents = totalInt % 100;
			System.out.println(dollars+" dollars and " + cents + " cents");
			
	}
}
