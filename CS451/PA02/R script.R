# Load necessary libraries
library(ggplot2)
library(reshape2)

# Create a data frame with the provided data
sorting_data <- data.frame(
  Array_Size = c(1000, 10000, 25000, 50000, 100000, 150000, 200000),
  Merge_Sort_Time = c(2, 8, 20, 14, 36, 80, 42),
  Quick_Sort_Time = c(0, 3, 4, 5, 24, 20, 20),
  Insertion_Sort_Time = c(5, 38, 216, 513, 1619, 4737, 8004)
)

# Melt the data for easier plotting
sorting_data_melted <- reshape2::melt(sorting_data, id.vars = "Array_Size", variable.name = "Sorting_Algorithm", value.name = "Running_Time")

# Plot with ggplot2
ggplot(sorting_data_melted, aes(x = Array_Size, y = Running_Time, color = Sorting_Algorithm, linetype = Sorting_Algorithm)) +
  geom_point(size = 3, shape = 16) +  # Using consistent shape for points
  geom_line(size = 1) +  # Increase line thickness
  geom_smooth(se = FALSE, method = "loess", aes(group = Sorting_Algorithm), color = "black", linetype = "dashed") +  # Adjusting smoother aesthetics
  scale_color_manual(values = c("blue", "red", "green")) +
  scale_linetype_manual(values = c("solid", "dashed", "solid")) +  # Adjusting line type
  labs(
    x = "Array Size (n)",
    y = "Running Time (ms)",
    title = "Running Time vs. Array Size",
    color = "Sorting Algorithm",
    linetype = "Sorting Algorithm",  # Better naming for linetype legend
    caption = "Smoothed lines represent trend"
  ) +
  theme_minimal() +
  theme(legend.position = "top", legend.title = element_text(size = 12), legend.text = element_text(size = 10))

