package epi;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    Set<Character> oddFreqChars = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (oddFreqChars.contains(c)) {
        oddFreqChars.remove(c);
      } else {
        oddFreqChars.add(c);
      }
    }
    return oddFreqChars.size() <= 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
