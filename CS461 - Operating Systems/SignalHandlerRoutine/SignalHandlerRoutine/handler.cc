/*
 * Program Name: handler.cc
 * Author: Mario Soto
 * Date: October 15, 2024
 *
 * Purpose: This program demonstrates how to handle signals SIGUSR1 and SIGUSR2 in a C++ program.
 *          When SIGUSR1 is received, the program prints the current date and time.
 *          When SIGUSR2 is received, the program prints the author's name.
 *          The program continuously runs in an infinite loop, awaiting signals, and handles any signal errors.
 */

#include <iostream>
#include <csignal>
#include <unistd.h>
#include <cstdlib>
#include <ctime>

// Function to print the current date and time when SIGUSR1 is received
void printDateTime() {
    time_t now = time(0);
    char* dt = ctime(&now);
    std::cout << "Current date and time: " << dt;
}

// Signal handler function
void signalHandler(int signum) {
    if (signum == SIGUSR1) {
        printDateTime();
    } else if (signum == SIGUSR2) {
        std::cout << "Program author: Mario Soto\n";
    }

    // Resetting the signal handler to ensure it works for future signals
    if (signal(signum, signalHandler) == SIG_ERR) {
        std::cerr << "Error resetting the signal handler for signal " << signum << ".\n";
        exit(1);  // Exit with error code 1 if there is an issue
    }
}

int main() {
    // Setting up signal handlers for SIGUSR1 and SIGUSR2
    if (signal(SIGUSR1, signalHandler) == SIG_ERR) {
        std::cerr << "Error setting signal handler for SIGUSR1\n";
        return 1;  // Exit with error code 1
    }

    if (signal(SIGUSR2, signalHandler) == SIG_ERR) {
        std::cerr << "Error setting signal handler for SIGUSR2\n";
        return 1;  // Exit with error code 1
    }

    // Infinite loop to keep the program running and awaiting signals
    while (true) {
        pause();  // Suspends the process until a signal is received
    }

    return 0;  // Program should never reach here
}
