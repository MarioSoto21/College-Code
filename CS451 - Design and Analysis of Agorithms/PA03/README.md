# Depth-first search in directed graphs

This Java program demonstrates the detection of Strongly Connected Components (SCCs) in directed graphs
 using Kosaraju's algorithm. It consists of multiple Java files and automates compilation and execution 
 using an Ant script.
 
## Files

- **Graph.java**: This file contains the following functionalities:
Generating a directed graph.
Encoding nodes and edges within the method. You can modify the code to encode a different graph or enable user input.
Implementing Kosaraju's algorithm to detect strongly connected components (SCCs).

- **Node.java**: This file represents the data structure used by Graph.java for graph construction.
KosarajuSCC.java: Implements Kosaraju's algorithm for detecting SCCs in a directed graph.

- **GraphGenerator.java**: Generates random directed graphs with nodes, edges, and attributes.

- **Runner.java**: The main class responsible for executing the program.

- **build.xml**: Ant script for automating compilation and execution.


## Requirements

- **Java JDK**: The program requires Java JDK to compile and run. Make sure you have it installed on your machine.
- **Apache Ant**: The program is automated using an Ant script, so you need to have Apache Ant installed.
    - To install Apache Ant, follow the instructions in the [official Apache Ant installation guide](https://ant.apache.org/manual/install.html).
    - For a tutorial on getting started with Apache Ant, check out the [Hello World with Apache Ant guide](https://ant.apache.org/manual/tutorial-HelloWorldWithAnt.html).

## Installation

1. Clone this repository to your local machine:

    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:

    ```bash
    cd <repository-name>
    ```

3. Ensure you have the required dependencies (Java JDK and Apache Ant) installed.

## Usage

To execute the program, simply run the following command in the terminal/console from the project directory:

```bash
ant
