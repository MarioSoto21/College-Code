# Makefile for compiling PinTool and Cache simulator
#
# Author: Mario Soto
# Date: 20 September 2024
# Description: This program simulates a virtually indexed, virtually tagged cache with LRU replacement              using Intel's Pin Tool for memory instrumentation.
#
CXX = g++
CXXFLAGS = -Wall -std=c++11

PIN_ROOT = /path/to/pin
PINTOOL_CXXFLAGS = -I$(PIN_ROOT)/source/include/pin -I$(PIN_ROOT)/extras/stlport/include

LDFLAGS = -lpthread

all: pintool

pintool: PinTool.o Cache.o
	$(CXX) $(CXXFLAGS) -o pintool PinTool.o Cache.o $(LDFLAGS)

PinTool.o: PinTool.cpp Cache.h
	$(CXX) $(CXXFLAGS) $(PINTOOL_CXXFLAGS) -c PinTool.cpp

Cache.o: Cache.cpp Cache.h
	$(CXX) $(CXXFLAGS) -c Cache.cpp

clean:
	rm -f *.o pintool
