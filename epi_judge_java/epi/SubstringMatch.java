package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    if (s.length() > t.length()) {
      return -1;
    }
    final int BASE = 26;
    int sPow = 1;
    int sHash = 0;
    int tHash = 0;
    // calculate hash for substrings
    for (int i = 0; i < s.length(); i++) {
      sPow = i > 0 ? sPow * BASE : 1;
      sHash = sHash * BASE + s.charAt(i);
      tHash = tHash * BASE + t.charAt(i);
    }

    // go thru and compute rolling hash
    for (int i = s.length(); i < t.length(); i++) {
      if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
        return i - s.length();
      }
      tHash -= sPow * t.charAt(i - s.length());
      tHash = tHash * BASE + t.charAt(i);
    }

    if (tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
      return t.length() - s.length();
    }

    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
