public class LinearFibonacci{
	public static long calculateFibonacciNumber(int n) {
		if (n == 0)
			return 0;
		long[] fib = new long[n +1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= n; i++) {
			fib[i] = fib[i -1] + fib[i - 2];
		}
		return fib[n];
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.println(calculateFibonacciNumber(n));
	}
}