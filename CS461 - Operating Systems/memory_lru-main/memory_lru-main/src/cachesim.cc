#include <iostream>
#include <stdlib.h>
#include "../include/Cache.h"
#include "../include/Memory.h"
#include <map>
#include <string>

using namespace std;

int main(int argc, char** argv)
{
	unsigned int l1_cache_size = atoi(argv[1]) * 1024;
	unsigned int num_ways = atoi(argv[2]);
	unsigned int block_size = atoi(argv[3]);
	unsigned int num_sets = l1_cache_size / (block_size / num_ways);
	unsigned short int replacement_policy = 1; //lru policy
	unsigned short int write_policy = 1; //write back
        int memorySizeKB = atoi(argv[4]); // Memory size in Kilobytes
	int frameSizeKB = atoi(argv[5]); // Frame, and hence, page size in Kilobytes

	mem::Cache* L1 = NULL;/*!< A pointer to an object representing L1 cache. */	
	L1 = new mem::Cache(
			"L1 Cache",
			num_sets,
			num_ways,
			block_size,
			replacement_policy,
			write_policy
			);

	mem::Memory* main_memory = NULL;/*!< A pointer to an object representing main memory. */	
	main_memory = new mem::Memory(
			memorySizeKB,
			frameSizeKB
			);
	// Define directories and file paths needed to execute the benchmark program
	string BENCHMARK_DIR = "ls";      // Directory containing the benchmark executable
	string PIN_DIR = "pin";              // Path to the Pin tool directory
	string PINFLAGS = " -t ";                        // Flag for running the Pin tool
	string PINTRACER_DIR = "./src/obj-intel64/memtracer.so -- ";  // Path to the Pin tracer (shared object) which traces memory accesses
	string benchmark = argv[15];                     // Takes the benchmark filename from the 15th argument
																									 // Construct the full command to execute the benchmark using the Pin tool and tracer
	BENCHMARK_DIR = PIN_DIR + PINFLAGS + PINTRACER_DIR + BENCHMARK_DIR;
	cout << BENCHMARK_DIR << endl;
	char buffer[128];                                // Buffer to store the output from the executed command
																									 // Open a pipe to run the constructed command and read its output
	FILE* pipe = popen((const char*)BENCHMARK_DIR.c_str(), "r");
	if (!pipe) throw runtime_error("popen() failed!");  // Throw an error if popen() fails to create the pipe
	try {
		// Loop to read the command output line by line
		while (fgets(buffer, sizeof(buffer), pipe) != NULL) {
			/**
			 * The assumption is that the output of the PinTool is in the format:
			 *      AccessType:Address
			 * For example:
			 *      W:0x00007fffffffe378   (Write access at address)
			 *      or R:0x00007fffffffe378 (Read access at address)
			 */
			string delimiter = ":";                       // Define the delimiter used in the output to separate AccessType and Address
			size_t pos = 0;
			string token;
			string ins = buffer;                          // Convert the buffer content to a string for easier manipulation
																										// Find the position of the delimiter ":" in the output
			if ((pos = ins.find(delimiter)) != string::npos) {
				token = ins.substr(0, pos);                 // Extract the AccessType (either "R" or "W")

				// If AccessType is "R" (read) or "W" (write), process it further
				if (token.compare("R") == 0 || token.compare("W") == 0) {
					string AccessType = ins.substr(0, ins.find(delimiter));  // Extract the AccessType
					ins.erase(0, ins.find(delimiter) + delimiter.length());  // Remove the AccessType part and keep the address
					//unsigned address = std::stoi(ins);     // Extract the address
					/**
					 * To Do:
					 * Call the Access method from the Cache class and pass the AccessType and address
					 * as arguments to handle the memory access simulation.
					 */
					if (ins.length() != 0)
					{
						unsigned long address = std::stoul(ins);
						//cout << address << "\n\t" << AccessType << "\t" << address << endl;
						bool isHit = L1->Access(address, static_cast<int>(AccessType[0]));
						if (!isHit) {
							main_memory->pageWalk(address, static_cast<int>(AccessType[0]));
							// simulate page walk by
						}
					}
				}
			}
		}

		std::cout << "Cache Stats" << std::endl;
		std::cout << "Hit Ratio: " << L1->getHitRatio() * 100
		<< "%\nMiss Ratio: " << L1->getMissRatio() * 100
		<< "%\nData Movement Size (Main Memory <-> Cache Memory): " 
		<< L1->getDataMovementSize() / 1024
		<< " MB" << std::endl; 

		std::cout << "Memory Stats" << std::endl;
		std::cout << "Hit Ratio: " << main_memory->getHitRatio() * 100
		<< "%\nMiss Ratio: " << main_memory->getMissRatio() * 100
		<< "%\nPages evicted: "
		<< main_memory->getEvectionRatio() << std::endl; 
	} catch (...) {
		// Ensure the pipe is closed and rethrow any caught exceptions
		pclose(pipe);
		throw;
	}

}
