#ifndef MEMORY_H
#define MEMORY_H

#include <list>
#include "Misc.h"
#include <memory>
#include <cmath>  // For log2

namespace mem
{
	struct PageTableEntry {
		int pageNumber;          // Page number
		int frameNumber;         // Frame number
		bool isUpdated;          // Indicates if the page has been updated
		bool isPresentInMemory;  // Indicates if the page is in main memory

		// Constructor to initialize fields
		PageTableEntry(int pageNum) 
			: pageNumber(pageNum), frameNumber(0), isUpdated(false), isPresentInMemory(false) {}
	};

	class Memory {
		private:
			int mainMemorySizeKB;                      // Main memory size allocated for a process (in KB)
			int frameSizeKB;                           // Frame size (equal to page size, in KB)
			std::unique_ptr<PageTableEntry[]> pageTable; // Array of structures representing the page table
			std::list<int> lruList;                    // List to track LRU order of page numbers

			// stats
			unsigned long num_hit = 0;
			unsigned long num_miss = 0;
			unsigned long num_evection = 0;

		public:
			// Constructor
			Memory(int memorySizeKB, int frameSizeKB);

			/*
			 * Simulates a page walk.
			 * When main memory is accessed, the `pageWalk` method is consulted first.
			 * The method calculates the page number by dividing the memory reference
			 * by the page size using integer division.
			 *
			 * Next, `pageWalk` checks whether the required page is in memory by consulting
			 * the Page Table. If the page is present in the Page Table and the access is a
			 * write operation, the `isUpdated` flag is set accordingly, and a page hit is recorded.
			 *
			 * If the page is not present in memory (page fault), a frame number is allocated
			 * to the page, mainMemorySizeKB should be decremented by the size of the new allocated frame, 
			 * and the other fields in the corresponding Page Table entry are updated.
			 */
			bool pageWalk(unsigned long address, int access_type);

			/*
			 * Handles page replacement using the LRU (Least Recently Used) algorithm.
			 *
			 * When no space is available in memory, the `handleLRUReplacement` method is
			 * consulted to determine the victim page for the page-in/page-out process.
			 * The `lruList` should be used efficiently to identify the page that has been
			 * least recently used and select it as the victim.
			 *
			 * In real-world scenarios, if the victim page has been updated (indicated by
			 * the `isUpdated` field being `true`), the page is paged out before the incoming
			 * page is loaded. Otherwise, no page-out occurs, and only the page-in process
			 * is performed for the incoming page.
			 *
			 * In this program, every page-out operation for updated pages should be
			 * recorded. At the end of the simulation, a detailed report of all page-out
			 * operations for updated pages must be generated.
			 */

			void handleLRUReplacement(int incomingPageNumber);

			double getHitRatio() {
				return static_cast<double>(num_hit) / (num_hit + num_miss);
			}


			double getMissRatio() { 
				return static_cast<double>(num_miss) / (num_hit + num_miss);
			};

			unsigned long getEvectionRatio() { 
				return num_evection;
			};

	};

}  // namespace mem
#endif // MEMORY_H

