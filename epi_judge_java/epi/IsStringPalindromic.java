package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromic {
  @EpiTest(testDataFile = "is_string_palindromic.tsv")

  public static boolean isPalindromic(String s) {
    int lowPtr = 0;
    int highPtr = s.length() - 1;
    while (lowPtr < highPtr) {
      while (!Character.isLetterOrDigit(s.charAt(lowPtr)) && lowPtr < highPtr) {
        lowPtr++;
      }

      while (!Character.isLetterOrDigit(s.charAt(highPtr)) && lowPtr < highPtr) {
        highPtr--;
      }

      if (Character.toLowerCase(s.charAt(lowPtr)) != Character.toLowerCase(s.charAt(highPtr))) {
        return false;
      }

      lowPtr++;
      highPtr--;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromic.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
