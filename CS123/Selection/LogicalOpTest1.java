public class LogicalOpTest1 {
		public static void main(String argv[]) {
			boolean cleanedRoom = false;
			boolean didHomework = false;
			
			
			if (cleanedRoom || didHomework) {
				System.out.println("You can go out");
			}
			else {
				System.out.println("No, you can't go out");
			}
			System.out.println("Done");
	}
}