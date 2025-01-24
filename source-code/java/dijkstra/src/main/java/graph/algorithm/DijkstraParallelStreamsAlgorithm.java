package graph.algorithm;

import constants.Constants;
import graph.entities.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of Dijkstra's parallel algorithm using streams.
 *
 * @author Alex_Smarandache
 */
public class DijkstraParallelStreamsAlgorithm implements IAlgorithm {

  /**
   * The graph to perform the Dijkstra's algorithm sequentially.
   */
  private final Graph graph;

  /**
   * The list with node items
   */
  private final List<NodeItem> nodes = new ArrayList<>();


  /**
   * Constructor.
   *
   * @param graph The graph to perform the Dijkstra's algorithm sequentially.
   */
  public DijkstraParallelStreamsAlgorithm(final Graph graph) {
    this.graph = graph;
    final int noOfNodes = graph.getNoOfNodes();

    for (int i = 0; i < noOfNodes; i++) {
      final NodeItem newNode = new NodeItem();
      newNode.id = i;
      nodes.add(newNode);
    }

    nodes.get(graph.getSourceNode()).distance = 0;
  }


  @Override
  public void solve() {
    final int noOfNodes = nodes.size();
    for(int i = 0; i < noOfNodes - 1; i++) {
      final Optional<NodeItem> currentMinNode = nodes
              .parallelStream().min((a, b) -> (!a.visited && b.visited || (!a.visited && !b.visited && a.distance < b.distance)) ? -1 : 1);
      if(currentMinNode.isPresent()) {
        final NodeItem nodeItem = currentMinNode.get();
        final int index = nodeItem.id;
        nodeItem.visited = true;
        nodes.parallelStream().forEach((node -> {
          if (!nodes.get(node.id).visited && graph.getAdjacencyMatrix().get(index).get(node.id) != 0
                  && nodes.get(index).distance != Constants.INFINITE
                  && nodes.get(index).distance + graph.getAdjacencyMatrix().get(index).get(node.id) < nodes.get(node.id).distance)
            node.distance = nodes.get(index).distance + graph.getAdjacencyMatrix().get(index).get(node.id);
        }));
      }
    }
  }

  /**
   * @return The graph.
   */
  public List<Integer> getDistances() {
    List<Integer> toReturn = new ArrayList<>();
    nodes.forEach(node -> toReturn.add(node.distance));
    return toReturn;
  }

  /**
   * @implNote Must be call the solve() method to process the valid distances.
   *
   * @return The distances between source node and the rest of nodes.
   */
  public Graph getGraph() {
    return graph;
  }

  /**
   * Class to store data about a node.
   */
  private class NodeItem  {
    /**
     * The distance between the current node and source node.
     */
    public int distance = Constants.INFINITE;

    /**
     * <code>true</code> if the node was visited.
     */
    public boolean visited = false;

    /**
     * The node id.
     */
    public int id;
  }
}
