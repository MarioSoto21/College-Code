# Command line arguments
CACHESIZE = 8
WAYS = 4
BLOCKSIZE = 64 # bytes

MEMORYSIZE = 2097152 # Kilobytes
FRAMESIZE = 4 # Kilobytes

# Define the compiler to use
CC = g++

# Define any compile-time flags
CCFLAGS = -std=c++11 -Wall -g

# Define the cpp source files
SRC_DIR = ./src
SRCS = $(wildcard $(SRC_DIR)/*.cc)  # Using .cc extension

# Define the target directory for object files
OBJ_DIR = ./target
OBJS = $(patsubst $(SRC_DIR)/%.cc,$(OBJ_DIR)/%.o,$(SRCS))  # Convert .cc to .o in target dir

# Define the target directory for the executable
MAIN_DIR = ./target
MAIN = $(MAIN_DIR)/CacheSim  # Save the executable in the target directory

all: $(OBJ_DIR) $(MAIN)
	@echo CacheSim has been compiled

# Create the target directory
$(OBJ_DIR):
	mkdir -p $(OBJ_DIR)  # Create the target directory if it doesn't exist

# Build the executable file
$(MAIN): $(OBJS)
	$(CC) $(CCFLAGS) -o $(MAIN) $(OBJS)

# Rule for building .o's from .cc's
$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cc
	$(CC) $(CCFLAGS) -c $< -o $@

# Run the executable file
run: $(MAIN)
	./$(MAIN) $(CACHESIZE) $(WAYS) $(BLOCKSIZE) $(MEMORYSIZE) $(FRAMESIZE)

# Remove the executable file, the .o object files, and backup files
clean:
	rm -rf $(OBJ_DIR)  # Delete the target directory
	find . -name '*.csv' -exec rm -f {} ';'
	find . -name '*.db' -exec rm -f {} ';'
	find . -name $(MAIN) -exec rm -f {} ';'
	rm -rf './docs/html'

# Ensure the target directory is created each time before compiling
.PHONY: all clean $(OBJ_DIR) run
