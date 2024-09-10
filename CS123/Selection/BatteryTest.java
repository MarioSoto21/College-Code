// Finish the following code so that it prints "Plug in your phone" if
// the battery is below 50, "Unplug your phone" if it is above 100, and
// "All okay" otherwise. Change the battery value to test all 3 conditions.
public class BatteryTest {
		public static void main(String argv[]) {
			int battery = 65;
			
			if (battery < 50) {
				System.out.println("Plug in your phone");
			}
			else if (battery >= 100) {
				System.out.println("Unplug your phone");
			}
			else {
				System.out.println("All okay");
			}
	}
}