# Makefile to compile and run handler.cc

# Compiler
CXX = g++

# Compiler flags
CXXFLAGS = -Wall -g

# Target executable
TARGET = handler

# Build the target
all: $(TARGET)

$(TARGET): handler.o
	$(CXX) $(CXXFLAGS) -o $(TARGET) handler.o

handler.o: handler.cc
	$(CXX) $(CXXFLAGS) -c handler.cc

# Run the program
run: $(TARGET)
	./$(TARGET)

# Clean the build files
clean:
	rm -f *.o $(TARGET)
