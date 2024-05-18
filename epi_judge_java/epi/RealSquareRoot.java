package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {
    double left, right;
    if (x < 1.0) {
      left = x;
      right = 1.0;
    } else {
      left = 1.0;
      right = x;
    }
    while (compare(left, right) != Ordering.EQUAL) {
      double mid = left + ((right - left) / 2);
      double midSqrd = mid * mid;
      if (compare(midSqrd, x) == Ordering.LARGER) {
        right = mid;
      } else {
        left = mid;
      }
    }
    return left;
  }

  private static enum Ordering {
    LARGER, EQUAL, SMALLER
  };

  private static Ordering compare(double a, double b) {
    final double EPSILON = 0.0000001;
    double diff = (a - b) / Math.max(Math.abs(a), Math.abs(b));
    if (diff < -EPSILON) {
      return Ordering.SMALLER;
    } else if (diff > EPSILON) {
      return Ordering.LARGER;
    }
    return Ordering.EQUAL;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
