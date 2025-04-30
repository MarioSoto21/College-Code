/*  Mario Soto
    October 22, 2024

    simple implementation of mergesort.

    Input:  from STDIN, integers in unsorted fashion.  Data
       ends when a 0 (zero) is read.

    Output: the data in sorted order

    Notes:  Since mergesort actually relies upon external storage to
       store the merge of two segments, we use an additional array to
       store the merge called "out[]" and then place the results
       back into "in[]" for subsequent merges.


*/

#include <iostream>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>
#include <vector>
#include <cstring>
#include <sys/wait.h>

/* value to size the arrays */
#define MAXNUM 10000

/* declare the arrays */
int *in; // pointer to the shared memory segment
std::vector<int> out(MAXNUM); // internal array for merging

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
    if (loc1 >= loc2) return; // base case

    int mid = (loc1 + loc2) / 2;
    sort(loc1, mid);          // sort the first half
    sort(mid + 1, loc2);      // sort the second half
    merge(loc1, mid, mid + 1, loc2); // merge the sorted halves
}

/* Helper function to print the array */
void printar(int NUM) {
    for (int i = 0; i <= NUM; ++i)
        std::cout << in[i] << std::endl;
    std::cout << std::endl;
}

int main() {
    int share_key_in;
    pid_t children[8]; // Array to store process IDs of the 8 children
    struct shmid_ds item;

    /* Get shared memory */
    if ((share_key_in = shmget(IPC_PRIVATE, MAXNUM * sizeof(int), IPC_CREAT | 0666)) < 0) {
        std::cerr << "Cannot get shared memory" << std::endl;
        return 1;
    }

    /* Attach shared memory segment */
    if ((in = (int *)shmat(share_key_in, nullptr, SHM_RND)) == (void *)-1) {
        std::cerr << "Cannot attach to shared memory" << std::endl;
        return 1;
    }
    // Manual input up to until 0 is typed
    // int i = -1, j;
    // bool done = false;

    // /* Read input data */
    // while (!done) {
    //     std::cin >> j;
    //     if (j == 0)
    //         done = true;
    //     else
    //         in[++i] = j;
    // }

    // Random 
    int i = -1;
    //int num_elements = rand() % MAXNUM + 1; // Random number of elements (1 to MAXNUM)
    int num_elements = 10;

    // Generate random input data
    for (i = 0; i < num_elements; ++i) {
        in[i] = rand() % 100; // Random values between 0 and 999
    }
    /* Print before sorting */
    printar(i);

    // If the number of elements is less than or equal to 8, sort directly without forking
    if (i <= 8) {
        sort(0, i);
        printar(i); // Print after sorting
        shmctl(share_key_in, IPC_RMID, &item); // Remove shared memory
    } else {
        int part_size = i / 8; // Calculate the size of each section for each process

        // Fork 8 child processes, each responsible for sorting one section
        for (int p = 0; p < 8; p++) {
            if ((children[p] = fork()) == 0) {
                // Child process code
                int start = p * part_size; // Start of the section
                int end = (p == 7) ? i : (start + part_size - 1); // End of the section
                // Reattach the shared memory in the child process
                if ((in = (int *)shmat(share_key_in, nullptr, SHM_RND)) == (void *)-1) {
                    std::cerr << "Cannot attach to shared memory" << std::endl;
                    return 1;
                }
                sort(start, end); // Sort the section
                return 0; // Child process exits after sorting
            }
        }

        // Parent process waits for all child processes to complete
        for (int p = 0; p < 8; p++) {
            int status;
            waitpid(children[p], &status, 0);
        }

        // Merge the 8 sorted sections pairwise
        for (int step = 1; step < 8; step *= 2) {
            for (int start = 0; start < 8; start += 2 * step) {
                int mid = ((start + step - 1) * part_size); // Calculate middle point
                int end = (start + 2 * step - 1) * part_size; // Calculate end point
                if (end > i) end = i; // Adjust end point if it exceeds the array
                merge(start * part_size, mid, mid + 1, end); // Merge two sections
            }
        }

        // Print the final sorted array
        printar(i);
        shmctl(share_key_in, IPC_RMID, &item); // Remove shared memory
    }

    return 0;
}

