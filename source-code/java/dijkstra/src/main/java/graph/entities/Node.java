package graph.entities;

/**
 * A node representation with needed information to perform Dijkstra algorithm.
 * 
 * @author alex_smarandache
 *
 */
public class Node implements Comparable<Node> {

  /**
   * The ID for current node.
   */
  private int id;

  /**
   * The distance between this node and a source node.
   */
  private int distance;

  /**
   * Constructor.
   * 
   * @param id       The ID for current node.
   * @param distance The distance between this node and a source node.
   */
  public Node(final int id, final int distance) {
    this.id = id;
    this.distance = distance;
  }

  /**
   * Setter for id field.
   * 
   * @param id The new id.
   */
  public void setNodeId(final int id) {
    this.id = id;
  }

  /**
   * Setter for distance field.
   * 
   * @param distance The new distance.
   */
  public void setDistance(final int distance) {
    this.distance = distance;
  }

  @Override
  public String toString() {
    return "Node{" +
        "id =" + id +
        ", distance=" + distance +
        '}';
  }

  /**
   * The nodes are compared by distance.
   */
  public int compareTo(final Node node) {
    return Integer.compare(distance, node.distance);
  }

  /**
   * @return The current id for this node.
   */
  public int getId() {
    return id;
  }

  /**
   * @return The current distance for this node.
   */
  public int getDistance() {
    return distance;
  }

}
