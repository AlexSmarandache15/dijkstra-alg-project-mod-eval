package graph.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

import constants.Constants;
import graph.algorithm.internal.parallel.DijkstraThread;
import graph.algorithm.internal.parallel.GraphInfo;
import graph.algorithm.internal.parallel.UpdaterRunnable;
import graph.entities.Graph;
import graph.entities.Node;

/**
 * Contains implementation for parallel Dijkstra's algorithm.
 * 
 * @author alex_smarandache
 * 
 */
public class DijkstraParallelThreadsAlgorithm implements IAlgorithm {

  /**
   * The default number of thread if is not specified by user.
   */
  private static final int DEFAULT_NO_OF_THREADS = 4;
  
  /**
   * The graph.
   */
  private final Graph graph;

  /**
   * An array with visited nodes status. <code>true</code> for items with indexes of the already visited nodes, 
   * <code>false</code> otherwise. 
   */
  private final boolean[] visited;
  
  /**
   * A list with a queue for each @DijkstraThread instance.
   */
  private final List<PriorityQueue<Node>> queues = new ArrayList<>();
  
  /**
   * List with distances. 
   * 
   * @implNote The item is something like: distances[nodeId] ---> distance for nodeID.
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
   * List of threads.
   */
  private final List<Thread> threads;
  
  /**
   * The number of the threads.
   */
  private final int noOfThreads;

  
  /**
   * Constructor. Will be <code>4</code> threads if this constructor is called.
   * 
   * @param graph The graph.
   */
  public DijkstraParallelThreadsAlgorithm(final Graph graph) {
    this(graph, DEFAULT_NO_OF_THREADS);
  }

  /**
   * Constructor.
   * 
   * @param graph       The graph.
   * @param noOfThreads The number of threads.
   */
  public DijkstraParallelThreadsAlgorithm(final Graph graph, final int noOfThreads) {
    final int noOfNodes = graph.getNoOfNodes();
    this.graph          = graph;
    threads             = new ArrayList<>(noOfThreads);
    distances           = new ArrayList<>(noOfNodes);
    visited             = new boolean[noOfNodes];
    isFinished          = new AtomicBoolean(false);
    currentNode         = new Node(graph.getSourceNode(), 0);
    this.noOfThreads    = noOfThreads;
    
    for(int i = 0; i < noOfNodes; i++) {
      distances.add(i, Constants.INFINITE);
      visited[i] = false;
    }

    distances.set(graph.getSourceNode(), 0);

    for(int i = 0; i < noOfThreads; i++) {
      queues.add(new PriorityQueue<Node>());
    }

    final UpdaterRunnable reduceRunnable = new UpdaterRunnable(queues, isFinished, visited, currentNode);
    final CyclicBarrier cyclicBarrier    = new CyclicBarrier(noOfThreads, reduceRunnable);

    int start = 0;
    int end = 0;
    final int threadPart = noOfNodes / noOfThreads;
    int restOfNodes = noOfNodes % noOfThreads;

    for (int i = 0; i < noOfThreads; i++) {

      start = end;
      end += threadPart;
      if (restOfNodes > 0) {
        end++;
        restOfNodes--;
      }

      final GraphInfo graphInfo = new GraphInfo(graph, start, end, currentNode, visited, distances);
      threads.add(new DijkstraThread(graphInfo, cyclicBarrier, queues.get(i), isFinished));
    }

  }

  @Override
  public void solve() {
    for(int i = 0; i < noOfThreads; i++) {
      threads.get(i).start();
    }
    for(int i = 0; i < noOfThreads; i++) {
      try {
        threads.get(i).join();
      } catch (InterruptedException e) {
        e.printStackTrace();
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
