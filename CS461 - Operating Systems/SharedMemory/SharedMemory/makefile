# Makefile to compile and run handler.cc

# Compiler
CC = g++

# Compiler flags
CFLAGS = -std=c++11 -Wall

# Target executable
TARGET = shared

# Build the target
all: $(TARGET)

# Link the object file to create the executable
$(TARGET): shared.o
	$(CC) $(CFLAGS) -o $(TARGET) shared.o

# Compile the source file to object file
shared.o: shared.cc
	$(CC) $(CFLAGS) -c shared.cc

# Remove object files and executable
clean:
	rm -f *.o $(TARGET)

# Run the program after building it
run: all
	./$(TARGET)

.PHONY: all clean run
