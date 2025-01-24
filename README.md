# dijkstra-alg-project-mod-eval
 
This is a study project of Dijkstra's algorithm for university, on the subject of parallel and distributed algorithms.
<br>
The project contains 7 implementations: 
- C++ Sequential
- Java Sequential(using priority queue)
- C++ STL Parallel
- C++ OpenMP
- C++ MPI
- Java using Parallel Streams
- Java using Threads

The input for each algorithm will be extracted from an "input.txt" file inside project folder. <br>
The input file has the following format: <br>
no_of_nodes source_node  <br>
c00 c01 ... c0(n-1)  <br>
c10 c11 ... c1(n-1)  <br>
...................  <br>
c(n-1)0 c(n-1)1 ... c(n-1)(n-1)
Where c is the adjacency matrix for graph.
<br>
The output will be given in a "output.txt" file inside the project folder. <br>
To run project I used:  
- Visual Studio IDE  
- Intellij IDE  <br>
To run MPI implementation, you must install the MPI support on Visual Studio IDE, see: 
-  https://docs.microsoft.com/en-us/message-passing-interface/microsoft-mpi
-  https://docs.microsoft.com/en-us/archive/blogs/windowshpc/how-to-compile-and-run-a-simple-ms-mpi-program
