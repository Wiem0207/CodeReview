/* 
 * programme AwesomePasswordChecker sous licence GNU GPL v3
 * Copyright © 2024 BembliWiem
 *
 * Ce programme est un logiciel libre : vous pouvez le redistribuer et/ou le modifier
 * selon les termes de la licence GNU General Public License publiée par la Free Software Foundation,
 * soit la version 3 de la licence, soit (à votre choix) une version ultérieure.
 *
 * Ce programme est distribué dans l'espoir qu'il sera utile,
 * mais SANS AUCUNE GARANTIE ; sans même la garantie implicite de
 * COMMERCIALISATION ou d'ADÉQUATION À UN BUT PARTICULIER. Voir la
 * GNU General Public License pour plus de détails.
 *
 * Vous devriez avoir reçu une copie de la GNU General Public License
 * avec ce programme. Si ce n'est pas le cas, consultez <https://www.gnu.org/licenses/>.
 */


package com.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/**
 * This class evaluates the strength of a given password.
 */
public class AwesomePasswordChecker {

  private static AwesomePasswordChecker instance;

  private final List<double[]> clusterCenters = new ArrayList<>();

  /**
   * Return the Unique instance of AwesomePasswordChecker, Creating it if
   * necessary. if the instance is already created,  this method simply return
   * this instance. if not, it will be initialized by the data from the File in
   * paramaters
   * @param file the file contaninng list/data used to initialize the instance
   * @return return a Unique instance od AwesomePasswordChecker.
   * @throws IOEXception if an I/O Error occurs while reading the File file
   */
  public static AwesomePasswordChecker getInstance(File file)
      throws IOException {
    if (instance == null) {
      instance = new AwesomePasswordChecker(new FileInputStream(file));
    }
    return instance;
  }

  /**
   * Return the Unique instance of AwesomePasswordChecker, Creating it if
   * necessary. if the instance is already created,  this method simply return
   * this instance. if not, it will be initialized by the data from the File
   * "cluster_centers_HAC_aff.csv"
   * @return return a Unique instance od AwesomePasswordChecker.
   * @throws IOEXception if an I/O Error occurs while reading the File
   *     "cluster_centers_HAC_aff.csv"
   */

  public static AwesomePasswordChecker getInstance() throws IOException {
    if (instance == null) {
      InputStream is =
          AwesomePasswordChecker.class.getClassLoader().getResourceAsStream(
              "cluster_centers_HAC_aff.csv");
      instance = new AwesomePasswordChecker(is);
    }
    return instance;
  }
  /**
   * A constructor used load the cluster center data from an external file or
   * resource using InputStream is.The stream should contain lines of numeric
   * values separated by ";"
   *
   * @param is inputStream containing cluster center data in a format
   *     where each line represents a cluster center with values separated by
   * semicolons.
   * @throws IOException If an I/O error occurs while reading from the input
   *     stream.
   */
  private AwesomePasswordChecker(InputStream is) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line;
    while ((line = br.readLine()) != null) {
      String[] values = line.split(";");
      double[] center = new double[values.length];

      for (int i = 0; i < values.length; ++i) {
        center[i] = Double.parseDouble(values[i]);
      }
      clusterCenters.add(center);
    }
    br.close();
  }
  /**
   * Generates a mask array for a given password. Each character in the password
   * is mapped to an integer according to predefined rules. The mapping is used
   * to classify characters into categories like vowels, uppercase and lowercase
   * letters, digits, and special characters.
   *
   * @param password The password string to be processed.
   * @return An integer array representing the mask for the password, where each
   * element corresponds to a character in the password. The values in the array
   * are assigned based on the following criteria:
   * <ul>
   * <li>('e', 's', 'a','i','t', 'n', 'r', 'u', 'o', 'l') are assigned 1.</li>
   * <li>('E', 'S', 'A', 'I','T', 'N', 'R', 'U', 'O', 'L') are assigned 3.</li>
   * <li>Special characters('>', '<', '-', '?', '.', '/', '!','%', '@', '&') are
   * assigned 6.</li>
   * <li>Lowercase  characters non mentionned above are assigned 2.</li>
   * <li>Uppercase characters non mentionned above are assigned 4.</li>
   * <li>Digits ('0'-'9') are assigned 5.</li>
   * <li>All other characters are assigned 7.</li>
   * </ul>
   */

  public int[] maskAff(String password) {
    int[] maskArray = new int[28];
    int limit = Math.min(password.length(), 28);

    for (int i = 0; i < limit; ++i) {
      char c = password.charAt(i);
      switch (c) // (e, s, a, i, t, n, r, u, o, l) are assigned the value 1.
      {
      case 'e':
      case 's':
      case 'a':
      case 'i':
      case 't':
      case 'n':
      case 'r':
      case 'u':
      case 'o':
      case 'l':
        maskArray[i] = 1;
        break;
      // (E, S, A, I, T, N, R, U, O, L) are assigned the value 3.
      case 'E':
      case 'S':
      case 'A':
      case 'I':
      case 'T':
      case 'N':
      case 'R':
      case 'U':
      case 'O':
      case 'L':
        maskArray[i] = 3;
        break;
      // Special characters (>, <, -, ?, ., /, !, %, @, &) are assigned the
      // value 6.
      case '>':
      case '<':
      case '-':
      case '?':
      case '.':
      case '/':
      case '!':
      case '%':
      case '@':
      case '&':
        maskArray[i] = 6;
        break;
      default:
        // Lowercase letters that are not mentionned above are assigned the
        // value 2.
        if (Character.isLowerCase(c)) {
          maskArray[i] = 2;
        }
        // Uppercase letters that are not vowels are assigned the value 4.
        else if (Character.isUpperCase(c)) {
          maskArray[i] = 4;
          // (0-9) are assigned the value 5.
        } else if (Character.isDigit(c)) {
          maskArray[i] = 5;
          // Any other characters are assigned the value 7
        } else {
          maskArray[i] = 7;
        }
      }
    }
    return maskArray;
  }
  /**
   * Calculates the minimum Euclidean distance between the mask representation of the given password 
   * and a set of predefined cluster centers. The method determines how closely the password matches 
   * one of the cluster centers.
   *
   * @param password The password string for which the distance needs to be calculated.
   * @return The minimum Euclidean distance between the mask of the password and the cluster centers.
   *         The distance is represented as a double value.
   */
  public double getDIstance(String password) {
    int[] maskArray = maskAff(password);
    double minDistance = Double.MAX_VALUE;
    for (double[] center : clusterCenters) {
      minDistance = Math.min(euclideanDistance(maskArray, center), minDistance);
    }
    return minDistance;
  }
    /**
   * Calculates a modified Euclidean distance between two arrays.
   * @param a An integer array representing the first point.
   * @param b A double array representing the second point.
   * @return The modified Euclidean distance between the two arrays as a double.
   */

    private double euclideanDistance(int[] a, double[] b) {
      double sum = 0;
      for (int i = 0; i < a.length; i++) {
        sum += (a[i] - b[i]) * (a[i] + b[i]);
      }
      return Math.sqrt(sum);
    }
    /**
     * Computes the MD5 hash of a given input string.
     *
     * This method implements the MD5 message-digest algorithm as specified
     * in RFC 1321. It processes the input string, applies padding and
     * bit-length encoding, and calculates the MD5 hash using non-linear functions
     * and constants. The output is a 128-bit hash represented as a hexadecimal string.
     *
     * @param input The input string to be hashed.
     * @return The MD5 hash of the input as a lowercase hexadecimal string.
     */
  public static String ComputeMD5(String input) {
    byte[] message = input.getBytes();
    int messageLenBytes = message.length;

    int numBlocks = ((messageLenBytes + 8) >>> 6) + 1;
    int totalLen = numBlocks << 6;
    byte[] paddingBytes = new byte[totalLen - messageLenBytes];
    paddingBytes[0] = (byte)0x80;

    long messageLenBits = (long)messageLenBytes << 3;
    ByteBuffer lengthBuffer = ByteBuffer.allocate(8)
                                  .order(ByteOrder.LITTLE_ENDIAN)
                                  .putLong(messageLenBits);
    byte[] lengthBytes = lengthBuffer.array();

    byte[] paddedMessage = new byte[totalLen];
    System.arraycopy(message, 0, paddedMessage, 0, messageLenBytes);
    System.arraycopy(paddingBytes, 0, paddedMessage, messageLenBytes,
                     paddingBytes.length);
    System.arraycopy(lengthBytes, 0, paddedMessage, totalLen - 8, 8);

    int[] h = {0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476};

    int[] k = {
        0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee, 0xf57c0faf, 0x4787c62a,
        0xa8304613, 0xfd469501, 0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
        0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821, 0xf61e2562, 0xc040b340,
        0x265e5a51, 0xe9b6c7aa, 0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
        0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed, 0xa9e3e905, 0xfcefa3f8,
        0x676f02d9, 0x8d2a4c8a, 0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
        0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70, 0x289b7ec6, 0xeaa127fa,
        0xd4ef3085, 0x04881d05, 0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
        0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039, 0x655b59c3, 0x8f0ccc92,
        0xffeff47d, 0x85845dd1, 0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
        0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391};

    int[] r = {7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
               5, 9,  14, 20, 5, 9,  14, 20, 5, 9,  14, 20, 5, 9,  14, 20,
               4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
               6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21};

    for (int i = 0; i < numBlocks; i++) {
      int[] w = new int[16];
      for (int j = 0; j < 16; j++) {
        w[j] = ByteBuffer.wrap(paddedMessage, (i << 6) + (j << 2), 4)
                   .order(ByteOrder.LITTLE_ENDIAN)
                   .getInt();
      }

      int a = h[0];
      int b = h[1];
      int c = h[2];
      int d = h[3];

      for (int j = 0; j < 64; j++) {
        int f, g;
        if (j < 16) {
          f = (b & c) | (~b & d);
          g = j;
        } else if (j < 32) {
          f = (d & b) | (~d & c);
          g = (5 * j + 1) % 16;
        } else if (j < 48) {
          f = b ^ c ^ d;
          g = (3 * j + 5) % 16;
        } else {
          f = c ^ (b | ~d);
          g = (7 * j) % 16;
        }
        int temp = d;
        d = c;
        c = b;
        b = b + Integer.rotateLeft(a + f + k[j] + w[g], r[j]);
        a = temp;
      }

      h[0] += a;
      h[1] += b;
      h[2] += c;
      h[3] += d;
    }

    // Step 5: Output
    ByteBuffer md5Buffer =
        ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
    md5Buffer.putInt(h[0]).putInt(h[1]).putInt(h[2]).putInt(h[3]);
    byte[] md5Bytes = md5Buffer.array();

    StringBuilder md5Hex = new StringBuilder();
    for (byte b : md5Bytes) {
      md5Hex.append(String.format("%02x", b));
    }

    return md5Hex.toString();
  }
}
