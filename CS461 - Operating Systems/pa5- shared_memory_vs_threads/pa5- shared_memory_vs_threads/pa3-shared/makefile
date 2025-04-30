# Makefile to compile and run pa3-shared

# Compiler
CC = g++

# Compiler flags
CFLAGS = -std=c++11 -Wall

# Target executable
TARGET = pa3-shared

# Build the target
all: $(TARGET)

# Link the object file to create the executable
$(TARGET): pa3-shared.o
	$(CC) $(CFLAGS) -o $(TARGET) pa3-shared.o

# Compile the source file to object file
pa3-shared.o: pa3-shared.cc
	$(CC) $(CFLAGS) -c pa3-shared.cc

# Remove object files and executable
clean:
	rm -f *.o $(TARGET)

# Run the program after building it
run: all
	./$(TARGET)

.PHONY: all clean run
