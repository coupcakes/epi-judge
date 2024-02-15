package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // XOR group of bits with each other
    x >>>= 32;
    x >>>= 16;
    x >>>= 8;
    x >>>= 4;
    x >>>= 2;
    x >>>= 1;
    return (short) (x & 1);
  }

  private static short parityBitByBit(long x) {
    short result = 0;
    while (x != 0) {
      result ^= 1;
      x &= (x - 1);
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
