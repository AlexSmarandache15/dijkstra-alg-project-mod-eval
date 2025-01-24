package graph.algorithm.internal.parallel;

import java.util.List;

import graph.entities.Graph;
import graph.entities.Node;

/**
 * Used to store mandatory information about graph for parallel Dijkstra's algorithm.
 * 
 * @author alex_smarandache
 */
public class GraphInfo {
  
  /**
   * The graph.
   */
  private final Graph graph;
  
  /**
   * The start node to process.
   */
  private final int startNode;
  
  /**
   * The end node to process.
   */
  private final int endNode;
  
  /**
   * The current node.
   */
  private final Node currentNode;
  
  /**
   * An array with visited nodes status. <code>true</code> for items with indexes of the already visited nodes, 
   * <code>false</code> otherwise. 
   */
  private final boolean[] visited;
  
  /**
   * List with distances. 
   * 
   * @implNote The item is something like: distances[nodeId] ---> distance for nodeID.
   */
  private final List<Integer> distances;
  
  
  /**
   * Constructor.
   *   
   * @param graph        The graph.
   * @param startNode    The start node to process.
   * @param endNode      The end node to process.
   * @param currentNode  The current node.
   * @param visited      An array with visited nodes status. <code>true</code> for items with indexes of the already visited nodes, 
   * <code>false</code> otherwise. 
   * @param distances    List with distances. 
   */
  public GraphInfo(final Graph graph, final int startNode, final int endNode,
      final Node currentNode, final boolean[] visited, final List<Integer> distances) {
    this.graph       = graph;
    this.startNode   = startNode;
    this.endNode     = endNode;
    this.currentNode = currentNode;
    this.visited     = visited;
    this.distances   = distances;
  }

  /**
   * @return The graph.
   */
  public Graph getGraph() {
    return graph;
  }

  /**
   * @return The start node to process.
   */
  public int getStartNode() {
    return startNode;
  }

  /**
   * @return The end node to process.
   */
  public int getEndNode() {
    return endNode;
  }

  /**
   * @return The current node.
   */
  public Node getCurrentNode() {
    return currentNode;
  }

  /**
   * @return  An array with visited nodes status. <code>true</code> for items with indexes of the already visited nodes, 
   * <code>false</code> otherwise. 
   */
  public boolean[] getVisited() {
    return visited;
  }

  /**
   * @return List with distances. 
   */
  public List<Integer> getDistances() {
    return distances;
  }
  
 
}
