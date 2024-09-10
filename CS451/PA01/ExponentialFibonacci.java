public class ExponentialFibonacci {
	public static long calculateFibonacciNumber(int n){
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return calculateFibonacciNumber(n-1) + calculateFibonacciNumber(n - 2);
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.println(calculateFibonacciNumber(n));
	}
}