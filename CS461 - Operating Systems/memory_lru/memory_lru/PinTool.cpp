/*
 * Author: Mario Soto
 * Date: 20 September 2024
 * Description: This program simulates a virtually indexed, virtually tagged cache with LRU replacement
 *              using Intel's Pin Tool for memory instrumentation.
 */

#include "Cache.h"
#include "pin.H"
#include <iostream>
#include <fstream>
#include <string.h>
#include <stdio.h>

// Command line arguments for PinTool
KNOB<bool> KnobFollowCalls(KNOB_MODE_WRITEONCE, "pintool", "follow_calls", "0", "specify if the instrumentation has to follow function calls between the markers. Default: false");
KNOB<std::string> KnobCacheFile(KNOB_MODE_WRITEONCE, "pintool", "cache_file", "cachedef", "specify cache definition file. Default: \"cachedef\"");

ADDRINT startCall = 0;
ADDRINT startIns = 0;
ADDRINT stopCall = 0;
ADDRINT stopIns = 0;

// Create cache object (you can configure the parameters as necessary)
Cache cache("L1 Cache", 64, 4, 64, 0, 1); // 64 sets, 4 ways, 64-byte block, write-back, write-allocate

// Callbacks for memory read/write
VOID MemRead(UINT64 addr, UINT32 size) {
    std::cout << "Memory Read Address: " << addr << std::endl;
    cache.Access(static_cast<unsigned>(addr), 0); // Access type 0 = read
}

VOID MemWrite(UINT64 addr, UINT32 size) {
    std::cout << "Memory Write Address: " << addr << std::endl;
    cache.Access(static_cast<unsigned>(addr), 1); // Access type 1 = write
}

// Instrumentation routine
VOID Instruction(INS ins, VOID *v) {
    if (INS_IsMemoryRead(ins) && INS_IsStandardMemop(ins)) {
        INS_InsertPredicatedCall(ins, IPOINT_BEFORE, (AFUNPTR)MemRead, IARG_MEMORYREAD_EA, IARG_MEMORYREAD_SIZE, IARG_END);
    }

    if (INS_IsMemoryWrite(ins)) {
        INS_InsertPredicatedCall(ins, IPOINT_BEFORE, (AFUNPTR)MemWrite, IARG_MEMORYWRITE_EA, IARG_MEMORYWRITE_SIZE, IARG_END);
    }
}

// Final routine to print cache stats
VOID Fini(int code, VOID *v) {
    // Optional: Add code to print cache hit/miss statistics here
    std::cout << "Program Finished - Cache Simulation Completed" << std::endl;
}

// Main function
int main(int argc, char *argv[]) {
    PIN_InitSymbols();

    if (PIN_Init(argc, argv)) {
        return 1;
    }

    INS_AddInstrumentFunction(Instruction, 0);
    PIN_AddFiniFunction(Fini, 0);

    // Start the program
    PIN_StartProgram();
    return 0;
}
