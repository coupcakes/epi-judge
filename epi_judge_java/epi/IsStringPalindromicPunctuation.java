package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    // TODO - you fill in here.
    int start = 0, end = s.length() - 1;
    while (start < end) {
      while (!Character.isLetterOrDigit(s.charAt(start)) && start < end) {
        start++;
      }

      while (!Character.isLetterOrDigit(s.charAt(end)) && start < end) {
        end--;
      }

      if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
        return false;
      }

      start++;
      end--;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
