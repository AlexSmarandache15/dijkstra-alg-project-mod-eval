package graph.algorithm.internal.parallel;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import graph.entities.Node;

/**
 * Helps @CyclicBarrier with update informations about thread progress when is needed. 
 * 
 * @author alex_smarandache
 *
 */
public class UpdaterRunnable implements Runnable {

  /**
   * A list with a queue for each @DijkstraThread instance.
   */
  private final List<PriorityQueue<Node>> queues;
  
  /**
   * An atomic boolean. <code>true</code> if the thread must finish the execution.
   */
  private final AtomicBoolean isFinished;
  
  /**
   * An array with visited nodes status. <code>true</code> for items with indexes of the already visited nodes, 
   * <code>false</code> otherwise. 
   */
  private final boolean[] visited;
  
  /**
   * The current node.
   */
  private final Node currentNode;

  /**
   * Constructor.
   * 
   * @param queues       A list with a queue for each @DijkstraThread instance.
   * @param isFinished   An atomic boolean. <code>true</code> if the thread must finish the execution.
   * @param visited      An array with visited nodes status. 
   * <code>true</code> for items with indexes of the already visited nodes, <code>false</code> otherwise. 
   * @param currentNode  The current node.
   */
  public UpdaterRunnable(List<PriorityQueue<Node>> queues, AtomicBoolean isFinished, boolean[] visited, Node currentNode) {
    this.queues      = queues;
    this.isFinished  = isFinished;
    this.visited     = visited;
    this.currentNode = currentNode;
  }

  @Override
  public void run() {
    while (true) {
      Node minNode = null;
      int queueIndex = 0;
      //search all queues for the global minimum node
      for (int i = 0; i < queues.size(); i++) {
        if (!queues.get(i).isEmpty()) {
          Node node = queues.get(i).peek();
          if (minNode == null || node.compareTo(minNode) < 0) {
            minNode = node;
            queueIndex = i;
          }
        }
      }
      if (minNode == null) { 
        isFinished.set(true);
        return;
      } else if (!visited[minNode.getId()]) { 
        visited[minNode.getId()] = true;
        currentNode.setNodeId(minNode.getId());
        currentNode.setDistance(minNode.getDistance());
        queues.get(queueIndex).remove();
        return;
      } else { 
        queues.get(queueIndex).remove();
      }
    }
  }
}