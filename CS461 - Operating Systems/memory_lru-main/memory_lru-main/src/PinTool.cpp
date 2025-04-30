#include "pin.H"
#include <iostream>
#include <fstream>
#include "../include/Cache.h"

// Command-line options
KNOB<bool> KnobFollowCalls(KNOB_MODE_WRITEONCE, "pintool", "follow_calls", "0", "specify if the instrumentation has to follow function calls between the markers. Default: false");
KNOB<std::string> KnobCacheFile(KNOB_MODE_WRITEONCE, "pintool", "cache_file", "cachedef", "specify if the file, where the cache object is defined. Default: \"cachedef\"");

// Cache instance (you can initialize it with parameters)
Cache cache("L1 Cache", 64, 8, 64, 0, 1); // Example: 64 sets, 8 ways, 64-byte block size, LRU replacement, write-back policy

// Callbacks for loads and stores
VOID MemRead(UINT64 addr, UINT32 size)
{
    cache.Access(addr, 0); // 0 for read
}

VOID MemWrite(UINT64 addr, UINT32 size)
{
    cache.Access(addr, 1); // 1 for write
}

// Instrument memory instructions
VOID Instruction(INS ins, VOID *v)
{
    if (INS_IsMemoryRead(ins) && INS_IsStandardMemop(ins))
    {
        INS_InsertPredicatedCall(ins, IPOINT_BEFORE, (AFUNPTR)MemRead, IARG_MEMORYREAD_EA, IARG_MEMORYREAD_SIZE, IARG_END);
    }

    if (INS_IsMemoryWrite(ins))
    {
        INS_InsertPredicatedCall(ins, IPOINT_BEFORE, (AFUNPTR)MemWrite, IARG_MEMORYWRITE_EA, IARG_MEMORYWRITE_SIZE, IARG_END);
    }
}

// Final function to print statistics
VOID Fini(int code, VOID * v)
{
    // Print cache statistics here
    std::cout << "Cache statistics: \n";
    // Use cache object to output stats
}

// Main function
int main(int argc, char *argv[])
{
    PIN_InitSymbols();

    if (PIN_Init(argc, argv))
    {
        return 1;
    }

    INS_AddInstrumentFunction(Instruction, 0);
    PIN_AddFiniFunction(Fini, 0);

    // Start program execution
    PIN_StartProgram();

    return 0;
}

