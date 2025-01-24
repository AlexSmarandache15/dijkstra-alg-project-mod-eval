package util;

import java.util.Random;

/**
 * Contains methods to generate random data.
 * 
 * @author alex_smarandache
 *
 */
public class RandomUtil {

  /**
   * The random numbers generator.
   */
  private static final Random RANDOM_GENERATOR = new Random();
  
  /**
   * Hidden constructor.
   */
  private RandomUtil() {
    // not needed
  }
  
  /**
   * @param minValue Minimum value for this number.
   * @param maxValue Maximum value for this number.
   * 
   * @return A random number between [minValue, maxValue].
   */
  public static int generateNumber(final int minValue, final int maxValue) {
    return RANDOM_GENERATOR.nextInt(maxValue - minValue + 1) + minValue;
  }

  /**
   * @param maxValue Maximum value for this number.
   * 
   * @return A random number between [0, maxValue].
   */
  public static int generateNumber(final int maxValue) {
    return generateNumber(0, maxValue);
  }
  
}
