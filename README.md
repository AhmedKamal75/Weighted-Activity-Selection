# Weighted Activity Selection

This project implements a dynamic programming solution for the weighted activity selection problem.

## Classes

The project contains two classes:

- `WeightedActivitySelection`: This class represents a set of activities and provides methods to load activities from a file and calculate the maximum possible weight that can be obtained by choosing a mutually-compatible set of activities.

- `Main`: This class contains the `main` method which loads activities from an input file, calculates the maximum possible weight, and writes it to an output file.

## Running the Project

To run the project, you need to create a runnable JAR file, and then you can run it using the `java` command.

### Creating a JAR file

1. Compile the Java files into bytecode:
```bash
javac *.java
```
2. Create a manifest file named `Manifest.txt` with the following content:
```txt
Main-Class: Main
```
3. Create the JAR file:
```bash
jar cfm <your-jar-file>.jar Manifest.txt *.class
```

### Running the JAR file

You can run the JAR file using the following command:
```bash
java -jar <your-jar-file>.jar <input-file>
```
Replace `<your-jar-file>` with the name of your JAR file and `<input-file>` with the path to the input file.

The program will create an output file in the same directory as the input file. The output file will contain the maximum possible weight that can be obtained by choosing a mutually-compatible set of activities.


## Formula
The dynamic programming solution for the weighted activity selection problem is based on the following recurrence relation:

Let `S[i,j]` be the set of activities that start after activity `a_i` finishes and that finish before activity `a_j` starts. Formally, `S[i,j] = {a_k ∈ S : f_i ≤ s_k < f_k ≤ s_j}`.

Let `c[i,j]` be the maximum total weight of a mutually-compatible set of activities in `S[i,j]`.

Then, we have:

`c[i,j] = 0` if `S[i,j]` is empty,

`c[i,j] = max {c[i,k] + c[k,j] + w_k : a_k ∈ S[i,j]}` if `S[i,j]` is not empty.

Here, `f_i` is the finish time of activity `a_i`, `s_k` is the start time of activity `a_k`, `f_k` is the finish time of activity `a_k`, `s_j` is the start time of activity `a_j`, and `w_k` is the weight of activity `a_k`.
