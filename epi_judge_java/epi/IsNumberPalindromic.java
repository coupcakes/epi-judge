package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    // TODO - you fill in here.
    if (x <= 0) {
      return x == 0;
    }
    final int numOfDigits = (int) (Math.log10(x)) + 1;
    int msdMask = (int) Math.pow(10, numOfDigits - 1);
    for (int i = 0; i < Math.floor(numOfDigits / 2); i++) {
      if ((x % 10) != (x / msdMask)) {
        return false;
      }
      x %= msdMask;
      x /= 10;
      msdMask /= 100;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
