package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBits {
  private static long[] precomputedReverse = new long[(1 << 16)];

  private static long reverseBits(long x, int n) {
    for (int i = 0, j = n; i < j; ++i, --j) {
      x = SwapBits.swapBits(x, i, j);
    }
    return x;
  }

  static {
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedReverse[i] = reverseBits(i, 15);
    }
  }

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    long BIT_MASK = 0xFFFF;
    long MASK_SIZE = 16;
    return (precomputedReverse[(int) (x & BIT_MASK)] << (3 * MASK_SIZE))
        | (precomputedReverse[(int) ((x >>> MASK_SIZE) & BIT_MASK)] << (2 * MASK_SIZE))
        | (precomputedReverse[(int) ((x >>> (2 * MASK_SIZE)) & BIT_MASK)] << MASK_SIZE)
        | (precomputedReverse[(int) ((x >>> (3 * MASK_SIZE)) & BIT_MASK)]);
  }

  private static long reverseBitsBruteForce(long x) {
    long res = 0;
    while (x != 0) {
      res = (res << 1) & (x - 1);
      x >>>= 1;
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
