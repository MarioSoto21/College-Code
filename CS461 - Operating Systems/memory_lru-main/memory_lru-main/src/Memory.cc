#include "../include/Memory.h"
#include <iostream>
#include <algorithm>

namespace mem {

// Constructor: Initializes memory, page table, and LRU list
Memory::Memory(int memorySizeKB, int frameSizeKB)
    : mainMemorySizeKB(memorySizeKB),
      frameSizeKB(frameSizeKB),
      pageTable(std::make_unique<PageTableEntry[]>(mainMemorySizeKB / frameSizeKB)) {

    // Initialize the page table with default values
    for (int i = 0; i < mainMemorySizeKB / frameSizeKB; ++i) {
        pageTable[i] = PageTableEntry(i);
    }
}

// Simulates a page walk
bool Memory::pageWalk(unsigned long address, int access_type) {
    int pageSize = frameSizeKB * 1024; // Convert KB to bytes
    int pageNumber = address / pageSize;

    if (pageNumber >= mainMemorySizeKB / frameSizeKB) {
        std::cerr << "Error: Address out of memory bounds!" << std::endl;
        return false;
    }

    PageTableEntry& page = pageTable[pageNumber];

    // Check if the page is in memory
    if (page.isPresentInMemory) {
        num_hit++;
        // Update LRU list
        lruList.remove(pageNumber);
        lruList.push_front(pageNumber);

        // Update the page if it was written to
        if (access_type == 1) { // Assume 1 represents write
            page.isUpdated = true;
        }

        return true; // Page hit
    }

    // Page fault handling
    num_miss++;
    if (lruList.size() < static_cast<size_t>(mainMemorySizeKB / frameSizeKB)) {
        // Memory has space; load the page
        page.isPresentInMemory = true;
        page.frameNumber = static_cast<int>(lruList.size()); // Assign the next frame
        lruList.push_front(pageNumber);
    } else {
        // No space available; replace a page
        handleLRUReplacement(pageNumber);
    }

    return false; // Page fault
}

// Handles page replacement using the LRU algorithm
void Memory::handleLRUReplacement(int incomingPageNumber) {
    // Identify the victim page
    int victimPageNumber = lruList.back();
    lruList.pop_back();

    PageTableEntry& victimPage = pageTable[victimPageNumber];

    // Handle page-out for updated pages
    if (victimPage.isUpdated) {
        std::cout << "Paging out updated page: " << victimPageNumber << std::endl;
        num_evection++;
        victimPage.isUpdated = false; // Clear the update flag
    }

    // Update victim page's entry
    victimPage.isPresentInMemory = false;

    // Load the incoming page
    PageTableEntry& incomingPage = pageTable[incomingPageNumber];
    incomingPage.isPresentInMemory = true;
    incomingPage.frameNumber = victimPage.frameNumber; // Reuse the victim's frame
    lruList.push_front(incomingPageNumber);
}

} // namespace mem
