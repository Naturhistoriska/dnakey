package se.nrm.dina.dnakey.portal.util;
 
import java.util.Random;
 

/**
 *
 * @author idali
 */
public class Util {
  
  private Random random;
  private static Util instance = null;

  public static Util getInstance() {
    synchronized (Util.class) {
      if (instance == null) {
        instance = new Util();
      }
    }
    return instance;
  }
  
  /**
   * 
   * This method retrieve a random number between passed in min and max number
   * 
   * @param min
   * @param max 
   * @return int
   */
  public int getRandomNumber(int min, int max) {
    random = new Random();
    return random.ints(min, (max + 1)).limit(1).findFirst().getAsInt();  
  }
  
  /**
   * This method check if the string line is empty
   * 
   * @param line
   * @return boolean
   */
  public boolean isEmptyLine(String line) {
    int start = 0;
    while (start < line.length()) {
      if (ConstantString.getInstance().getWhiteSpaceChars().indexOf(line.charAt(start)) == -1) {
        break;
      }
      start++;
    }
    line = line.substring(start);
    return line.length() == 0;
  } 
  
  /**
   * This method count a string array and set the maximum length to 99
   *  
   * @param strings
   * @return int
   */
  public int getMaxCount(String... strings) {
    return strings.length > 100 ? 99 : strings.length;
  }
}
