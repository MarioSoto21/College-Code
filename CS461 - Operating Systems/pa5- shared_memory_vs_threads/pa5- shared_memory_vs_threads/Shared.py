import matplotlib.pyplot as plt

# Data from terminal (replace with actual times for each workload)
workload_sizes = [2, 4, 8, 16, 64, 128, 256, 512, 1024, 2048, 4096]
user_times = [0.000, 0.001, 0.000, 0.001, 0.003, 0.000, 0.003, 0.014, 0.005, 0.017, 0.015]
sys_times = [0.045, 0.049, 0.044, 0.073, 0.056, 0.041, 0.096, 0.084, 0.087, 0.071, 0.093]
total_times = [0.311, 0.351, 0.327, 0.376, 0.329, 0.313, 0.422, 0.641, 0.427, 0.365, 0.445]

# Create the plot
plt.figure(figsize=(10, 6))

# Plot each time category
plt.plot(workload_sizes, user_times, label='User Time', marker='o')
plt.plot(workload_sizes, sys_times, label='System Time', marker='o')
plt.plot(workload_sizes, total_times, label='Total Time', marker='o')

# Add labels and title
plt.xlabel('Workload Size')
plt.ylabel('Time (seconds)')
plt.title('Time Taken for Different Workload Sizes')

# Add a legend
plt.legend()

# Customize x-axis and y-axis to improve visibility of ticks
plt.xscale('log')  # Use logarithmic scale for x-axis
plt.yscale('log')  # Use logarithmic scale for y-axis

# Customize the tick labels to show meaningful numbers on log scale
plt.xticks([2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096], [str(i) for i in [2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096]])
plt.yticks([0.0001, 0.001, 0.01, 0.1, 1, 10, 100], [f'{i:.4f}' for i in [0.0001, 0.001, 0.01, 0.1, 1, 10, 100]])

# Add gridlines and make them visible
plt.grid(True, which="both", linestyle="--", linewidth=0.7)

# Save the plot to a file
plt.savefig('shared_workload_time_plot.png')

# Show the plot
plt.show()