
public class OperatorTest {
		public static void main(String argv[]) {
			System.out.println(1/3); // 0
			System.out.println(1.0/3); // .3333
			System.out.println(1/3.0); // .3333
			System.out.println((double)1/3); //.3333
			System.out.println((double)(1/3)); // 0.0
		}
}