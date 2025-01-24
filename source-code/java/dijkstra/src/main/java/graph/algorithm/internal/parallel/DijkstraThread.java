package graph.algorithm.internal.parallel;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

import graph.entities.Graph;
import graph.entities.Node;

/**
 * A thread for Dijkstra algorithm.
 * 
 * @author alex_smarandache
 *
 */
public class DijkstraThread extends Thread {

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
   * An array with visited nodes status. <code>true</code> for items with indexes of the already visited nodes, 
   * <code>false</code> otherwise. 
   */
  private final boolean[] visited;
  
  /**
   * The barrier.
   */
  private final CyclicBarrier cyclicBarrier;
  
  /**
   * The nodes queue.
   */
  private final PriorityQueue<Node> queue;
  
  /**
   * The list of distances.
   */
  private final List<Integer> distances;
  
  /**
   * An atomic boolean. <code>true</code> if the thread must finish the execution.
   */
  private final AtomicBoolean isFinished;
  
  /**
   * The current node.
   */
  private final Node currentNode;
  
  
  /**
   * Constructor.
   * 
   * @param graphInfo      Store graph information.
   * @param cyclicBarrier  The barrier.
   * @param queue          The nodes queue.
   * @param isFinished     An atomic boolean. <code>true</code> if the thread must finish the execution.
   */
  public DijkstraThread(final GraphInfo graphInfo, CyclicBarrier cyclicBarrier, 
      final PriorityQueue<Node> queue, final AtomicBoolean isFinished) {
    this.graph         = graphInfo.getGraph();
    this.startNode     = graphInfo.getStartNode();
    this.endNode       = graphInfo.getEndNode();
    this.visited       = graphInfo.getVisited();
    this.distances     = graphInfo.getDistances();
    this.currentNode   = graphInfo.getCurrentNode();
    this.cyclicBarrier = cyclicBarrier;
    this.queue         = queue;
    this.isFinished    = isFinished;
  }
  
  @Override
  public void run() {
      while (!isFinished.get()) {
          processNeighbours(currentNode.getId());
          try {
              //wait for all threads to finish the iteration
              cyclicBarrier.await();
          } catch (InterruptedException | BrokenBarrierException e) {
              e.printStackTrace();
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
      for (int i = startNode; i < endNode; i++) {
          if (!visited[i] && neighbours.get(i) > 0) {
              final int newDistance = distances.get(node) + neighbours.get(i);
              if (newDistance < distances.get(i)) {
                  distances.set(i, newDistance);
                  queue.add(new Node(i, newDistance));
              }
          }
      }
  }

}
