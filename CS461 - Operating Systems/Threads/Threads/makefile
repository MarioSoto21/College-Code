# Makefile to compile and run thread.cc

# Compiler
CC = g++

# Compiler flags
CFLAGS = -lpthread -O2

# Target executable
TARGET = thread_program

# Build the target
all: $(TARGET)

# Link the source file directly to create the executable
$(TARGET): thread.cc
	$(CC) thread.cc $(CFLAGS) -o $(TARGET)

# Remove object files and executable
clean:
	rm -f $(TARGET)

# Run the program after building it
run: all
	./$(TARGET)

.PHONY: all clean run
