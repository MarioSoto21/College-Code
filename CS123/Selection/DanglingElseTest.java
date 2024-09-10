public class DanglingElseTest {
		public static void main(String argv[]) {
			boolean sunny = true;
			boolean hot = false;
			if(sunny) 
				if(hot)
				System.out.println("Head for the beach!");
			
				else 
				System.out.println("Bring your umbrella!");
			System.out.println("Done");
	}
}