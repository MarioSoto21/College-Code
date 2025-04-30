public class OldMcDonaldSong {
	
	public static void verse(String animal, String noise) { // parameters
		System.out.println("OldMcDonald had a farm");
		System.out.println("E-I-E-I-O");
		System.out.println("And on that farm he had a " + animal);
		System.out.println("E-I-E-I-O");
		System.out.println("With a " + noise + "-" + noise + " here");
		System.out.println("With a " + noise + "-" + noise + " there");
		System.out.println("Here a " + noise + ", there a " + noise);
		System.out.println("Old Mc Donald had a farm");
		System.out.println("E-I-E-I-O");
	}	
	public static void main(String[] ars ) {
		verse("cow", "moo"); // arguments
		verse("duck", "quack");
		verse("cat", "meow");
	}
}