package graph.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import constants.Constants;
import graph.entities.Graph;
import graph.entities.Node;

/**
 * Contains implementation for sequential Dijkstra's algorithm.
 * 
 * @author alex_smarandache
 *
 */
public class DijkstraSequentialAlgorithm implements IAlgorithm {

  /**
   * The graph to perform the Dijkstra's algorithm sequentially.
   */
  private final Graph graph;
  
  /**
   * An array with visited nodes status. <code>true</code> for items with indexes of the already visited nodes, 
   * <code>false</code> otherwise. 
   */
  private final boolean[] visited;
  
  /**
   * A priority queue with nodes.
   */
  private final PriorityQueue<Node> nodesQueue = new PriorityQueue<>();
  
  /**
   * List with distances. 
   * 
   * @implNote The item is something like: distances[nodeId] ---> distance for nodeID.
   */
  private final List<Integer> distances;

  
  /**
   * Constructor.
   * 
   * @param graph The graph to perform the Dijkstra's algorithm sequentially.
   */
  public DijkstraSequentialAlgorithm(final Graph graph) {
    this.graph = graph;
    final int noOfNodes = graph.getNoOfNodes();
    distances = new ArrayList<>(noOfNodes);
    visited   = new boolean[noOfNodes];

    for (int i = 0; i < noOfNodes; i++) {
      distances.add(i, Constants.INFINITE);
      visited[i] = false;
    }

    nodesQueue.add(new Node(graph.getSourceNode(), 0));
    distances.set(graph.getSourceNode(), 0);
  }

  
  @Override
  public void solve() {
    while (!nodesQueue.isEmpty()) {
      final int currentNode = nodesQueue.remove().getId();
      if (!visited[currentNode]) {
        visited[currentNode] = true;
        processNeighbours(currentNode);
      }
    }

  }
  
  /**
   * Process neighbours for the given node.
   * 
   * @param node The node if to process its neighbours.
   */
  private void processNeighbours(final int node) {
    final List<Integer> neighbours = graph.getAdjacencyMatrix().get(node);
    neighbours.parallelStream().forEach(neighbour -> {

    });
    for (int i = 0; i < neighbours.size(); i++) {
      if (!visited[i] && neighbours.get(i) > 0) {
        final int newDistance = distances.get(node) + neighbours.get(i);
        if (newDistance < distances.get(i)) {
          distances.set(i, newDistance);
          nodesQueue.add(new Node(i, newDistance));
        }
      }
    }
  }

  /**
   * @implNote Must be call the solve() method to process the valid distances.
   * 
   * @return The distances between source node and the rest of nodes.
   */
  public List<Integer> getDistances() {
    return distances;
  }

  /**
   * @return The graph.
   */
  public Graph getGraph() {
    return graph;
  }
  
  
}
