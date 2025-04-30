public class VolumeExample {
		
	
	public static double volumeCylinder(double radius, double height) {
		return Math.PI * radius * radius * height;
	}
		public static void main(String[] args ) {
			// Calculate the voule of a cylinder radius = 4 and height = 10
			double vol = volumeCylinder(4,10);
			System.out.println(vol);

	}
}