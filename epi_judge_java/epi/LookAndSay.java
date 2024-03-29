package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    String s = "1";
    for (int i = 1; i < n; i++) {
      s = nextNum(s);
    }
    return s;
  }

  private static String nextNum(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      int count = 1;
      while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        i++;
        count++;
      }
      sb.append(count).append(s.charAt(i));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
