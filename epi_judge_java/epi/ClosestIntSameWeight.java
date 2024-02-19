package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    return closestIntSameBitCountConstant(x);
    // TODO - you fill in here.
    // int NUM_OF_UNSIGNED_BITS = 63;
    // for (int i = 0; i < NUM_OF_UNSIGNED_BITS - 1; i++) {
    // if (((x >>> i) & 1) != ((x >>> (i + 1)) & 1)) {
    // // swap bits
    // x ^= ((1L << i) | (1L << (i + 1)));
    // return x;
    // }
    // }
    // throw new IllegalArgumentException("Value contains all 0's or all 1's");
  }

  public static long closestIntSameBitCountConstant(long x) {
    long mask = 0;
    if ((x & 1) == 1) {
      // get the rightmost 0
      mask = (x + 1) & ~x;
    } else {
      // get the rightmost 1
      mask = (~x + 1) & x;
    }

    return x ^ (mask | (mask >>> 1));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
