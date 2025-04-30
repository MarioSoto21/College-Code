/*
    Mario Soto
    November 15, 2024

    Multi-threaded sorting example.
    This code spawns 8 threads, each sorting a part of an array.
    The results are merged after sorting.

    Compile with:
        g++ thread.cc -lpthread -o thread_program
*/

#include <iostream>
#include <pthread.h>
#include <vector>
#include <algorithm> // For std::sort

// Structure to pass array segments to threads
struct Input {
    int* arr;  // Pointer to the array to be sorted
    int start; // Starting index of the segment
    int end;   // Ending index of the segment (exclusive)
};

// Thread function that sorts a portion of the array
void* sortArray(void* arg) {
    Input* input = static_cast<Input*>(arg); // Cast the void pointer to an Input pointer

    // Sort the segment of the array from 'start' to 'end'
    std::sort(input->arr + input->start, input->arr + input->end);

    return nullptr; // Thread returns no value
}

int main() {
    const int size = 80;      // Total size of the array
    const int numThreads = 8; // Number of threads (and array segments)
    int arr[size];            // Array to be sorted

    // Fill the array with random numbers between 0 and 99
    for (int i = 0; i < size; ++i) {
        arr[i] = rand() % 100;
    }

    // Print the array before sorting
    std::cout << "Array before sorting: ";
    for (int i = 0; i < size; ++i) {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;

    pthread_t threads[numThreads];   // Array to hold thread identifiers
    Input threadData[numThreads];    // Array to hold thread data structures
    int segmentSize = size / numThreads; // Calculate the size of each segment

    // Create threads to sort different segments of the array
    for (int i = 0; i < numThreads; ++i) {
        // Set up the data structure for each thread
        threadData[i].arr = arr;
        threadData[i].start = i * segmentSize;
        threadData[i].end = (i == numThreads - 1) ? size : (i + 1) * segmentSize; // Last thread may cover a larger segment if necessary

        // Create the thread and check for errors
        if (pthread_create(&threads[i], nullptr, sortArray, &threadData[i]) != 0) {
            std::cerr << "Error creating thread " << i << std::endl;
            exit(1);
        }
    }

    // Wait for all threads to finish
    for (int i = 0; i < numThreads; ++i) {
        pthread_join(threads[i], nullptr);
    }

    // Merge sorted segments using a simple merge logic
    std::vector<int> mergedArr(arr, arr + segmentSize); // Start with the first segment
    for (int i = 1; i < numThreads; ++i) {
        // Extract the next segment
        std::vector<int> segment(arr + i * segmentSize, arr + ((i == numThreads - 1) ? size : (i + 1) * segmentSize));
        // Temporary array to store merged results
        std::vector<int> temp(mergedArr.size() + segment.size());
        // Merge the current merged array with the next segment
        std::merge(mergedArr.begin(), mergedArr.end(), segment.begin(), segment.end(), temp.begin());
        // Update mergedArr with the new merged result
        mergedArr = temp;
    }

    // Display the sorted array
    std::cout << "Sorted array: ";
    for (int num : mergedArr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0; // End of program
}
