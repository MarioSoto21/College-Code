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
    pid_t children[8];
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

    int num_elements = 10; // Example number of elements
    for (int i = 0; i < num_elements; ++i) {
        in[i] = rand() % 100;
    }

    /* Print before sorting */
    std::cout << "Before sorting:" << std::endl;
    printar(num_elements - 1);

    int segment_size = num_elements / 8;
    for (int i = 0; i < 8; ++i) {
        if ((children[i] = fork()) == 0) {
            int start = i * segment_size;
            int end = (i == 7) ? num_elements - 1 : (start + segment_size - 1);
            if ((in = (int *)shmat(share_key_in, nullptr, SHM_RND)) == (void *)-1) {
                std::cerr << "Cannot attach to shared memory" << std::endl;
                return 1;
            }
            sort(start, end);
            return 0;
        }
    }

    for (int i = 0; i < 8; ++i) {
        waitpid(children[i], nullptr, 0);
    }

    /* Sequentially merge the segments */
    for (int i = 1; i < 8; ++i) {
        int start1 = 0;
        int end1 = i * segment_size - 1;
        int start2 = i * segment_size;
        int end2 = (i == 7) ? num_elements - 1 : (start2 + segment_size - 1);
        merge(start1, end1, start2, end2);
    }

    /* Print after sorting */
    std::cout << "After sorting:" << std::endl;
    printar(num_elements - 1);

    /* Detach and remove shared memory */
    shmctl(share_key_in, IPC_RMID, &item);
    return 0;
}
