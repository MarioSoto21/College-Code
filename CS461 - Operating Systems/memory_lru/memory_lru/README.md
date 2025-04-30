/*
 * Author: Mario Soto
 * Date: 20 September 2024
 * Description: This program simulates a virtually indexed, virtually tagged cache with LRU replacement
 *              using Intel's Pin Tool for memory instrumentation.
 */
 
# Cache Simulation using Pin Tool

## Description
This project implements a virtually indexed, virtually tagged cache simulator using the Pin tool framework. The cache uses an LRU replacement policy to simulate memory operations such as reads and writes.

## Features
- Simulates an LRU cache with configurable sets, ways, and block size.
- Tracks statistics such as hit rate, miss rate, evictions, and data movement.
- Supports both read and write memory operations.

## Requirements
- Pin tool installed and properly configured on your system.
- C++ compiler (e.g., g++) with C++11 support.

## Building
1. Clone or download the project.
2. Modify the `PIN_ROOT` variable in the `Makefile` to point to your Pin Tool installation path.
3. Run the following command to compile the project:
   ```bash
   make
Running
./pintool -- <path_to_your_program>
