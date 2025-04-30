/*
    Mario Soto
    November 25, 2024

    Fully multi-threaded sorting and merging example.
    Spawns threads for both sorting and merging phases.

    Compile with:
        g++ thread_merge_sort.cpp -lpthread -o thread_merge_sort
*/

#include <iostream>
#include <pthread.h>
#include <vector>
#include <random>
#include <cmath>

// Global shared arrays
const int MAXNUM = 10000; // Max size for arrays
int in[MAXNUM];          // Input array
int out[MAXNUM];         // Auxiliary array for merging

// Structure to pass data to threads
struct Input {
    int start; // Starting index of the segment
    int end;   // Ending index of the segment (inclusive)
};

/* Merge function that merges two sorted halves of the array */
void merge(int loc1, int loc2, int loc3, int loc4) {
    int i = loc1, j = loc3, insert = loc1;

    while (i <= loc2 && j <= loc4) {
        if (in[i] > in[j])
            out[insert++] = in[j++];
        else
            out[insert++] = in[i++];
    }

    // Handle any remaining elements
    while (i <= loc2)
        out[insert++] = in[i++];
    while (j <= loc4)
        out[insert++] = in[j++];

    // Copy merged data back to the original array
    for (i = loc1; i <= loc4; ++i)
        in[i] = out[i];
}

/* Recursive sort function */
void sort(int loc1, int loc2) {
    if (loc1 >= loc2) return; // Base case
    int mid = (loc1 + loc2) / 2;
    sort(loc1, mid);          // Sort the first half
    sort(mid + 1, loc2);      // Sort the second half
    merge(loc1, mid, mid + 1, loc2); // Merge the sorted halves
}

/* Thread function for sorting */
void* sortArray(void* arg) {
    Input* input = static_cast<Input*>(arg); // Cast the void pointer to an Input pointer
    sort(input->start, input->end);          // Sort the segment
    return nullptr;                          // Thread returns no value
}

/* Thread function for merging */
void* mergeArray(void* arg) {
    Input* input = static_cast<Input*>(arg);
    int mid = (input->start + input->end) / 2;
    merge(input->start, mid, mid + 1, input->end);
    return nullptr;
}

int main() {
    const int size = 80;      // Total size of the array
    const int numThreads = 8; // Number of threads for sorting
    pthread_t threads[numThreads];   // Thread identifiers for sorting
    Input threadData[numThreads];    // Thread data for sorting
    int segmentSize = size / numThreads; // Size of each segment

    // Fill the array with random numbers between 0 and 99
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dist(0, 99);
    for (int i = 0; i < size; ++i) {
        in[i] = dist(gen);
    }

    // Print the array before sorting
    std::cout << "Array before sorting:\n";
    for (int i = 0; i < size; ++i) {
        std::cout << in[i] << " ";
    }
    std::cout << "\n\n";

    // Create threads to sort segments
    for (int i = 0; i < numThreads; ++i) {
        threadData[i].start = i * segmentSize;
        threadData[i].end = (i == numThreads - 1) ? size - 1 : (i + 1) * segmentSize - 1;

        if (pthread_create(&threads[i], nullptr, sortArray, &threadData[i]) != 0) {
            std::cerr << "Error creating sorting thread " << i << std::endl;
            return 1;
        }
    }

    // Wait for sorting threads to finish
    for (int i = 0; i < numThreads; ++i) {
        pthread_join(threads[i], nullptr);
    }

    // Multi-threaded merging
    int mergeStep = 1;
    while (mergeStep < numThreads) {
        int mergeThreads = numThreads / (mergeStep * 2); // Number of merge threads
        pthread_t mergeThreadPool[mergeThreads];
        Input mergeThreadData[mergeThreads];

        for (int i = 0; i < mergeThreads; ++i) {
            int start = i * (2 * mergeStep) * segmentSize;
            int end = std::min(start + (2 * mergeStep * segmentSize) - 1, size - 1);

            mergeThreadData[i].start = start;
            mergeThreadData[i].end = end;

            if (pthread_create(&mergeThreadPool[i], nullptr, mergeArray, &mergeThreadData[i]) != 0) {
                std::cerr << "Error creating merging thread " << i << std::endl;
                return 1;
            }
        }

        // Wait for merge threads to finish
        for (int i = 0; i < mergeThreads; ++i) {
            pthread_join(mergeThreadPool[i], nullptr);
        }

        mergeStep *= 2; // Double the step size
    }

    // Print the sorted array
    std::cout << "Sorted array:\n";
    for (int i = 0; i < size; ++i) {
        std::cout << in[i] << " ";
    }
    std::cout << "\n";

    return 0;
}
