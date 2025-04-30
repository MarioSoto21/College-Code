# README for Signal Handler Program

## Program Name: handler.cc

### Overview:
This program demonstrates how to handle signals SIGUSR1 and SIGUSR2 in a C++ program.
- Upon receiving SIGUSR1, the program prints the current date and time.
- Upon receiving SIGUSR2, the program prints the author's name (Mario Soto).
- The program runs indefinitely, awaiting signals, and handles any signal errors gracefully.

### How to Compile and Run:
1. Ensure you have a C++ compiler (e.g., g++) installed.
2. Open a terminal and navigate to the directory containing the `handler.cc` file.
3. To compile the program, run:
make
4. To run the program in the background, use:
make run
5. To send signals to the program, open another terminal and use:
kill -USR1 <pid> # Print current date and time 
kill -USR2 <pid> # Print author's name
Replace `<pid>` with the process ID of the running program (find using `ps`).

### Error Handling:
- If there are errors in setting signal handlers, the program will indicate and exit with a return code 1.

### Cleanup:
To remove the compiled files, run:

