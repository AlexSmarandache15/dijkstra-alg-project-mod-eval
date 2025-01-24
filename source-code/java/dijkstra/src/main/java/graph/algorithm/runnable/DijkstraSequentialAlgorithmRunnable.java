package graph.algorithm.runnable;

import java.io.IOException;
import java.time.Instant;

import constants.IOConstants;
import graph.algorithm.DijkstraSequentialAlgorithm;
import graph.util.IOUtil;

/**
 * Used to run an instance of @{@link DijkstraSequentialAlgorithm}.
 * 
 * @author alex_smarandache
 *
 */
public class DijkstraSequentialAlgorithmRunnable implements Runnable {
  
  /**
   * The Dijkstra algorithm to run.
   */
  private final DijkstraSequentialAlgorithm dijkstraSeq;
  
  /**
   * Constructor.
   * 
   * @param dijkstraSeq The Dijkstra algorithm to run.
   */
  public DijkstraSequentialAlgorithmRunnable(final DijkstraSequentialAlgorithm dijkstraSeq)  {
    this.dijkstraSeq = dijkstraSeq;
  }

  @Override
  public void run() {
    final Instant start = Instant.now();
    dijkstraSeq.solve();
    final Instant end = Instant.now();
    final long startTimeMicros = start.getEpochSecond() * 1000_000 + start.getNano() / 1000;
    final long endTimeMicros = end.getEpochSecond() * 1000_000 + end.getNano() / 1000;
    System.out.println("Dijkstra sequential time: " + (endTimeMicros - startTimeMicros) + " microseconds.");
    
    try {
      IOUtil.writeResults(IOConstants.OUTPUT_FILE_SEQ, dijkstraSeq.getDistances(), 
          dijkstraSeq.getGraph().getSourceNode());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
