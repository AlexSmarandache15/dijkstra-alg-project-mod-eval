package graph.entities;

import java.util.List;

/**
 * A graph representation with needed information to perform Dijkstra algorithm.
 *  
 * @author alex_smarandache
 *
 */
public class Graph {

  /**
   * The source node.
   */
  private final int sourceNode;
  
  /**
   * The number of nodes.
   */
  private final int noOfNodes;
  
  /**
   * Adjacency matrix for this graph.
   */
  private final List<List<Integer>> adjacencyMatrix;
  
  /**
   * Constructor. In this case, the source node will be <code>0</code>.
   * 
   * @param adjacencyMatrix Adjacency matrix for this graph.
   */
  public Graph(final List<List<Integer>> adjacencyMatrix) {
    this(adjacencyMatrix, 0);
  }
  
  /**
   * Constructor.
   * 
   * @param adjacencyMatrix Adjacency matrix for this graph.
   * @param sourceNode      The source node.
   */
  public Graph(final List<List<Integer>> adjacencyMatrix, final int sourceNode) {
    this.adjacencyMatrix = adjacencyMatrix;
    this.sourceNode = sourceNode;
    this.noOfNodes = adjacencyMatrix.size();
  }

  /**
   * @return The source node.
   */
  public int getSourceNode() {
    return sourceNode;
  }

  /**
   * @return The number of nodes.
   */
  public int getNoOfNodes() {
    return noOfNodes;
  }

  /**
   * @return The adjacency matrix for this graph.
   */
  public List<List<Integer>> getAdjacencyMatrix() {
    return adjacencyMatrix;
  }
  
  
}
