k <- 50
ns <- 2 + (1:k)
runtime <- vector(length = k)

for (i in 1:k) {
  n <- ns[i]
  # Measure execution time of Java program
  command <- paste("java ExponentialFibonacci", n)
  # Measure execution time and convert to microseconds
  runtime[i] <- system.time(system(command, intern = TRUE))["user.self"] * 1e6
}

# Plotting the results
plot(ns, runtime, type = "b", xlab = "n", ylab = "runtime (microsecond)")
grid(col = "blue")
