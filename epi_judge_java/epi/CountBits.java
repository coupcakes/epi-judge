package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class CountBits {
  @EpiTest(testDataFile = "count_bits.tsv")

  public static short countBits(int x) {
    short numberOfBits = 0;
    while (x != 0) {
      numberOfBits += (x & 1);
      x >>>= 1;
    }
    return numberOfBits;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountBits.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
