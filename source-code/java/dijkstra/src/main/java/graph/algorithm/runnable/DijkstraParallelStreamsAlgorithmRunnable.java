package graph.algorithm.runnable;

import constants.IOConstants;
import graph.algorithm.DijkstraParallelStreamsAlgorithm;
import graph.util.IOUtil;

import java.io.IOException;
import java.time.Instant;

/**
 * Used to run an instance of @{@link DijkstraParallelStreamsAlgorithm}.
 * 
 * @author alex_smarandache
 *
 */
public class DijkstraParallelStreamsAlgorithmRunnable implements Runnable {

  /**
   * The Dijkstra algorithm to run.
   */
  private final DijkstraParallelStreamsAlgorithm dijkstraPar;

  /**
   * Constructor.
   *
   * @param dijkstraPar The Dijkstra algorithm to run.
   */
  public DijkstraParallelStreamsAlgorithmRunnable(final DijkstraParallelStreamsAlgorithm dijkstraPar)  {
    this.dijkstraPar = dijkstraPar;
  }

  @Override
  public void run() {
    final Instant start = Instant.now();
    dijkstraPar.solve();
    final Instant end = Instant.now();
    final long startTimeMicros = start.getEpochSecond() * 1000_000 + start.getNano() / 1000;
    final long endTimeMicros = end.getEpochSecond() * 1000_000 + end.getNano() / 1000;
    System.out.println("Dijkstra parallel streams time: " + (endTimeMicros - startTimeMicros) + " microseconds.");
    
    try {
      IOUtil.writeResults(IOConstants.OUTPUT_FILE_STR_PAR, dijkstraPar.getDistances(),
          dijkstraPar.getGraph().getSourceNode());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
