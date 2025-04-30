#include "pin.H"
#include <iostream>
#include <fstream>
#include <string.h>
#include <stdio.h>

//pin way of adding commandline parameters
//bool, if function calls are in the instrumented region or not
KNOB<bool> KnobFollowCalls(KNOB_MODE_WRITEONCE, "pintool", "follow_calls", "0", "specify if the instrumentation has to follow function calls between the markers. Default: false");
//path to the cache definition file
KNOB<std::string> KnobCacheFile(KNOB_MODE_WRITEONCE, "pintool", "cache_file", "cachedef", "specify if the file, where the cache object is defined. Default: \"cachedef\"");

//instruction and function addresses as markers for the instrumentation
ADDRINT startCall = 0;
ADDRINT startIns = 0;
ADDRINT stopCall = 0;
ADDRINT stopIns = 0;

//callbacks for loads and stores, without checks
 VOID MemRead(UINT64 addr, UINT32 size)
{
    //L1->Access(static_cast<long int>(addr), 1);
  std::cout << 'R' << ":" << static_cast<long int>(addr) << "\n";
}
 VOID MemWrite(UINT64 addr, UINT32 size)
{
    //L1->Access(static_cast<long int>(addr), 2);
  std::cout << 'W' << ":" << static_cast<long int>(addr) << "\n";
}

// instrumentation routine inserting the callbacks to memory instructions
VOID Instruction(INS ins, VOID *v)
{
        const AFUNPTR readFun = (AFUNPTR) MemRead;
        const AFUNPTR writeFun = (AFUNPTR) MemWrite;
        
        if (INS_IsMemoryRead(ins) && INS_IsStandardMemop(ins))
        {
            INS_InsertPredicatedCall(
                ins, IPOINT_BEFORE, readFun,
                IARG_MEMORYREAD_EA,
                IARG_MEMORYREAD_SIZE,
                IARG_END);
        }

        if(INS_IsMemoryWrite(ins))
        {
            INS_InsertPredicatedCall(
                ins, IPOINT_BEFORE, writeFun,
                IARG_MEMORYWRITE_EA,
                IARG_MEMORYWRITE_SIZE,
                IARG_END);
        }
}

// print stats, when instrumented program exits
VOID Fini(int code, VOID * v)
{

}

int main(int argc, char *argv[])
{
    PIN_InitSymbols();

    // Initialize PIN library. Print help message if -h(elp) is specified
    // in the command line or the command line is invalid 
    if( PIN_Init(argc,argv) )
    {
        return 1;
    }

    INS_AddInstrumentFunction(Instruction, 0);
    PIN_AddFiniFunction(Fini, 0);
    
    // Start the program, never returns
    PIN_StartProgram();
    
    return 0;
}
