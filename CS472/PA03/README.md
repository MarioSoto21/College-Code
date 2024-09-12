# Sorting Algorithms Test Suite

This project contains a test suite for testing sorting algorithms implemented in Java.

## Prerequisites

- Java Development Kit (JDK) installed on your system
- Apache Ant installed on your system

## Build Instructions

1. Clone this repository to your local machine.
2. Place JUnit Jar in lib
3. Open a terminal or command prompt and navigate to the root directory of the project.
4. Run the following command to build the project:

ant compile

This will compile the source code located in the `src/main` directory and generate class files in the `build/classes` directory.

## Running Tests

After building the project, you can run the test suite to verify the correctness of the implemented sorting algorithms. Follow these steps:

1. Ensure that the project is built using the `ant compile` command.
2. Run the following command to execute the test suite:

ant test

This will compile the test source code located in the `src/test` directory and run the tests. If all tests pass successfully, you will see a "BUILD SUCCESSFUL" message in the terminal.

To run the Java program that sorts and searches for a number provided by the user, use:

ant run

To clean up by deleting the classes directory, use:
ant clean


## Additional Information

- The `build.xml` file in the root directory contains the Ant build script for compiling and running the tests.
- The source code for the sorting algorithms is located in the `src/main/sorting` directory.
- The test code is located in the `src/test/sorting` directory.
- The `lib` directory is reserved for any external libraries or dependencies. It used the JUnit 4 library (`junit-4.13.2.jar`) required for running the tests.
- The `build` directory will be automatically created during the build process and will contain the compiled class files.


