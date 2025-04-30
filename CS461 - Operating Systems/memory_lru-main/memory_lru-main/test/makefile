CC = g++

LIBS = -lgtest_main -lgtest -lpthread

CCFLAGS = -std=c++11 -Wall -g

# define the cpp source files
 SRC_DIR = .
 SRCS = $(wildcard $(SRC_DIR)/*.cpp)

DEPEND_DIR = ../src

# define the C object files
OBJS = $(SRCS:.cpp=.o)

MAIN = CacheSim

all: $(MAIN)

$(MAIN): $(OBJS)
	$(CC) $(LIBS) $(CCFLAGS) -o $(MAIN) $(OBJS) $(DEPEND_DIR)/Cache.cc

clean:
	find . -name '*.o' -exec rm -f {} ';'
	find . -name $(MAIN) -exec rm -f {} ';'

.cpp.o:
	$(CC) $(CCFLAGS) -I. -c -o $@ $<
# run the executable file
run:
	./$(MAIN)
