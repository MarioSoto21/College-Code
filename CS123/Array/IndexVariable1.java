public class IndexVariable1 {
	public static void main(String[] args) {
		String[] names = {"Jamal", "Emily", "Destinny", "Mateo",
							"Sofia"};
		int index = 1;
		/*
		System.out.println(names[index - 1]);
		index++; // 2
		System.out.println(names[index]);
		System.out.println(names[index/2]);
		names[index] = "Rafi"; // 2
		index--; // 1
		System.out.println(names[index+1]); // 2
		*/
		System.out.println(names[index - 1]);
		System.out.println(names[++index]); //names[index++]
		System.out.println(names[index/2]);
		names[index] = "Rafi";
		System.out.println(names[--index+1]);
		
	}
}
