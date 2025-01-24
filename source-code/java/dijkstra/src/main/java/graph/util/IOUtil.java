package graph.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import graph.entities.Graph;

/**
 * Contains methods for IO for @Graph.
 * 
 * @author alex_smarandache
 *
 */
public class IOUtil {

  /**
   * Hidden constructor.
   */
  private IOUtil() {
    // not needed
  }

  /**
   * Write the given graph to a file.
   * 
   * @param fileName The file name for graph writing.
   * @param graph    The graph to be written.
   * 
   * @throws IOException When IO problems appears.
   */
  public static void writeGraph(final String fileName, final Graph graph) throws IOException  {
    final File file = new File(fileName);
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath())) {
      bufferedWriter.append(String.valueOf(graph.getNoOfNodes())).append(" ")
      .append(String.valueOf(graph.getSourceNode())).append('\n');
      graph.getAdjacencyMatrix().forEach(row -> {
        try {
          for (Integer val : row) {
            bufferedWriter.append(String.valueOf(val)).append(' ');
          }
          bufferedWriter.append('\n');
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    } 
  }

  /**
   * Create a graph with data from a file.
   * 
   * @param fileName The file name to extract data.
   * 
   * @return The created graph.
   * 
   * @throws IOException When IO problems appears.
   */
  public static Graph parseGraph(final String fileName) throws IOException {
    final List<List<Integer>> adjacencyMatrix = new ArrayList<>();
    int sourceNode;
    int noOfNodes;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      String line = bufferedReader.readLine();
      if (line != null) {
        final String[] params = line.split(" ");
        noOfNodes = Integer.parseInt(params[0]);
        sourceNode = Integer.parseInt(params[1]);
      } else {
        throw new IOException("Input file must begin with the number of nodes and the source node.");
      }
      while ((line = bufferedReader.readLine()) != null) {
        final List<Integer> row = new ArrayList<>(noOfNodes);
        final String[] values = line.split(" ");
        for (String value : values) {
          int cost = Integer.parseInt(value);
          row.add(cost);
        }
        adjacencyMatrix.add(row);
      }
    } 

    return new Graph(adjacencyMatrix, sourceNode);
  }

  /**
   * Write results in a file.
   * 
   * @param fileName   The file name to write result.
   * @param distances  The resulted distances to write in the file.
   * 
   * @throws IOException 
   */
  public static void writeResults(final String fileName, final List<Integer> distances, final int sourceNode) throws IOException  {
    final File file = new File(fileName);
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath())) {
      bufferedWriter.append("Vertex \tDistance from ").append(sourceNode + "").append("(source node)\n");
      int node= 0;
      for (Integer value : distances) {
        bufferedWriter.append(node + " \t\t")
        .append(value != Constants.INFINITE ? String.valueOf(value) : "Infinite")
        .append('\n');
        node++;
      }
    } 
  }
}
