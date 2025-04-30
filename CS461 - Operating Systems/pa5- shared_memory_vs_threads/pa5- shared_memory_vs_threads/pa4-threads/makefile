# Makefile to compile and run pa4-threads

# Compiler
CC = g++

# Compiler flags
CFLAGS = -lpthread -O2

# Target executable
TARGET = pa4-threads

# Build the target
all: $(TARGET)

# Link the object file to create the executable
$(TARGET): pa4-threads.o
	$(CC) $(CFLAGS) -o $(TARGET) pa4-threads.o

# Compile the source file to object file
pa4-threads.o: pa4-threads.cc
	$(CC) -c pa4-threads.cc $(CFLAGS)

# Remove object files and executable
clean:
	rm -f *.o $(TARGET)

# Run the program after building it
run: all
	./$(TARGET)

.PHONY: all clean run
