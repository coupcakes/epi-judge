package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

  public static String decoding(String s) {
    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        count = (count * 10) + c - '0';
      } else {
        while (count > 0) {
          sb.append(c);
          count--;
        }
      }
    }
    return sb.toString();
  }

  public static String encoding(String s) {
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 1; i <= s.length(); i++) {
      if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
        sb.append(count).append(s.charAt(i - 1));
        count = 1;
      } else {
        count++;
      }
    }
    return sb.toString();
  }

  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
