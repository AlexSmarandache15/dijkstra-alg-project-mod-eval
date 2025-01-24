package constants;

/**
 * Contains IO constants.
 * 
 * @author alex_smarandache
 *
 */
public class IOConstants {

  /**
   * Hidden constructor.
   */
  private IOConstants() {
    // not needed
  }

  /**
   * The input file name.
   */
  public static final String INPUT_FILE = "input.txt";
  
  /**
   * The file name to write the distances resulted by applying Dijkstra sequential algorithm.
   */
  public static final String OUTPUT_FILE_SEQ = "output_seq.txt";
  
  /**
   * The file name to write the distances resulted by applying Dijkstra parallel threads algorithm.
   */
  public static final String OUTPUT_FILE_THR_PAR = "output_par_thr.txt";

  /**
   * The file name to write the distances resulted by applying Dijkstra parallel streams algorithm.
   */
  public static final String OUTPUT_FILE_STR_PAR = "output_par_str.txt";

}
