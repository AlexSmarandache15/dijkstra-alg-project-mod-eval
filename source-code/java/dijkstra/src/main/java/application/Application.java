 package application;

import java.io.IOException;

import constants.IOConstants;
import graph.algorithm.DijkstraParallelStreamsAlgorithm;
import graph.algorithm.DijkstraParallelThreadsAlgorithm;
import graph.algorithm.DijkstraSequentialAlgorithm;
import graph.algorithm.runnable.DijkstraParallelStreamsAlgorithmRunnable;
import graph.algorithm.runnable.DijkstraParallelThreadsAlgorithmRunnable;
import graph.algorithm.runnable.DijkstraSequentialAlgorithmRunnable;
import graph.util.IOUtil;

/**
 * Used to run the application.
 * 
 * @author alex_smarandache
 *
 */
public class Application {

  /**
   * Hidden constructor.
   */
  private Application() {
    // not needed
  }

  /**
   * Used to run the application.
   * 
   * @param args - args[0] contains strategy to run this application: 
   * <br>
   * <code>all</code>(run all impl, sequential and parallel(both with threads or streams)
   * <br>
   * <code>seq</code>(run sequential variant) 
   * <br>
   * <code>par_thr</code>(run parallel variant(threads)
   * <br>
   * <code>par_str</code>(run parallel variant(streams)
   * <br>
   * The default value is <code>all</code>.
   * <br>
   * You can specify the number of threads for the parallel algorithm with args[1].
   * 
   * @throws IOException 
   */
  public static void main(String... args) throws IOException {
    if(args.length == 0) {
      new DijkstraParallelThreadsAlgorithmRunnable(new DijkstraParallelThreadsAlgorithm(
              IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
      new DijkstraParallelStreamsAlgorithmRunnable(new DijkstraParallelStreamsAlgorithm(
              IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
      new DijkstraSequentialAlgorithmRunnable(new DijkstraSequentialAlgorithm(
          IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
    } else {
      if(args[0].equals("all") || args[0].equals("par_thr")) {
        if(args.length > 1) {
          if(args[0].equals("all") || args[0].equals("par_thr")) {
            new DijkstraParallelThreadsAlgorithmRunnable(new DijkstraParallelThreadsAlgorithm(
                    IOUtil.parseGraph(IOConstants.INPUT_FILE), Integer.parseInt(args[1]))).run();
          }
        } else {
          new DijkstraParallelThreadsAlgorithmRunnable(new DijkstraParallelThreadsAlgorithm(
                  IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
        }
        if(args[0].equals("all")) {
          new DijkstraParallelStreamsAlgorithmRunnable(new DijkstraParallelStreamsAlgorithm(
                  IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
          new DijkstraSequentialAlgorithmRunnable(new DijkstraSequentialAlgorithm(
              IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
        }
      } else {
        if(args[0].equals("par_str")) {
          new DijkstraParallelStreamsAlgorithmRunnable(new DijkstraParallelStreamsAlgorithm(
                  IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
        } else {
          new DijkstraSequentialAlgorithmRunnable(new DijkstraSequentialAlgorithm(
                  IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
        }
      }
    }
  }

}
