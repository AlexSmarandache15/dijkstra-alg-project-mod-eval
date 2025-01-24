package graph.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import constants.IOConstants;
import graph.entities.Graph;
import graph.util.IOUtil;
import util.RandomUtil;

/**
 * Contains methods to generate graph.
 * 
 * @author alex_smarandache
 *
 */
public class GraphGenerator {
  
  /**
   * Hidden constructor.
   */
  private GraphGenerator() {
    // not needed
  }
  
  /**
   * Generate a random graph.
   * 
   * @param noOfNodes The number of nodes.
   * @param density   The density of graph. If density is @Constants.MIN_DENSITY, no edge will be generated.
   * If density is @Constants.MAX_DENSITY, edges between all nodes will be generated.
   * 
   * @return The generated graph.
   */
  public static Graph generateGraph(final int noOfNodes, final int density) {
    List<List<Integer>> adjacencyMatrix = new ArrayList<>(noOfNodes);
    for (int i = 0; i < noOfNodes; i++) {
        List<Integer> row = new ArrayList<>();
        for (int j = 0; j < noOfNodes; j++) {
            final int grade = RandomUtil.generateNumber(Constants.MIN_DENSITY, Constants.MAX_DENSITY);
            if (i == j) {
                row.add(j, 0);
            } else {
              row.add(j, grade > density ? Constants.INFINITE : 
                RandomUtil.generateNumber(Constants.MIN_EDGE_COST, Constants.MAX_EDGE_COST)
              );
            }
        }
        adjacencyMatrix.add(i, row);
    }
    
    return new Graph(adjacencyMatrix);
  }
  
  /**
   * Used to generate input data.
   * 
   * @param args not needed.
   * 
   * @throws IOException When a IO problem appear.
   */
  public static void main(String[] args) throws IOException {
    IOUtil.writeGraph(IOConstants.INPUT_FILE, GraphGenerator.generateGraph(5, 70));
  }

}
