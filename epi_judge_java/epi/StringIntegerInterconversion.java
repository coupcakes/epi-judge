package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

  public static String intToString(int x) {
    boolean isNegative = false;
    if (x < 0) {
      isNegative = true;
    }

    StringBuilder sb = new StringBuilder();
    do {
      // must do Math.abs here because x might be equal to
      // Integer.MIN_VALUE... if it is, then the value remains the same (stays
      // negative)
      sb.append((char) ('0' + Math.abs(x % 10)));
      x /= 10;
    } while (x != 0);
    return sb.append(isNegative ? "-" : "").reverse().toString();
  }

  public static int stringToInt(String s) {
    return (s.charAt(0) == '-' ? -1
        : 1) * s.substring((s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0).chars().reduce(0,
            (acc, curr) -> acc * 10 + curr - '0');
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
