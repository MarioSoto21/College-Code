// Assume you have a package with a given height 3 inches and width 5
// inches. If the packages is roated90 degress, you should swap the
// values for the height and width. The code below makes an attempt 
// to swap the values stored in two variables h and w, which 
// represent height and iwidth. Unfortunately this code ahas an error
// and does not work. Fix he code tot perform a correct wap of h and w.
// You need to add a new variable named temp to use for the swap.
public class WrongSwap {
	public static void main(String argv[]) {
		int h = 3;
		int w = 5;
		int temp = h;
		System.out.println(h);
		System.out.println(w);
		h = w;
		w = temp;
		System.out.println(h);
		System.out.println(w);
	}
}