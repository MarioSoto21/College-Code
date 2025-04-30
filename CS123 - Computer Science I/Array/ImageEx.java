public class ImageEx {
	public static void main(String[] args) {
		String[] images = {"cow.jpg", "kitten.jpg", "puppy.jpg", "pig.jpg",
							"reindeer.jpg"};
		// Change index to see differrent image in the array!
		// Can yoy have it pick out a random image?
		int index = (int)(Math.random()*5);
		printHTMLimage(images[index]);
	}
	
	public static void printHTMLimage(String filename) {
		String baseURL = "http://raw.githubusercontent.com//bhoffman0/CSAwsome/";
		System.out.print("<img src=" + baseURL + filename + ">");
	
	}
}
