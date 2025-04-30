# README for Threaded Array Sorting Program

## Program Name: thread.cc

### Overview:
   This program demonstrates multi-threaded sorting using 8 threads. Each thread sorts a portion of the array, and the sorted results are merged into a final sorted array.

### How to Compile and Run:
1. Ensure you have a C++ compiler (e.g., g++) installed.
2. Open a terminal and navigate to the directory containing the `thread.cc` file.
3. To compile the program, run:
make
4. To run the program in the background, use:
make run
5. To clean up the compiled files, run:
make clean

### Note:
   Ensure that the program is compiled with `g++` and linked with the `-lpthread` flag.