package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

  public static void reverseWords(char[] input) {
    // TODO - you fill in here.
    reverse(0, input.length - 1, input);
    int start = 0, end = 0;
    int n = input.length;
    while (start < n) {
      while (start < end || (start < n && input[start] == ' ')) {
        start++;
      }
      while (end < start || (end < n && input[end] != ' ')) {
        end++;
      }

      reverse(start, end - 1, input);
    }
    return;
  }

  private static void reverse(int start, int end, char[] input) {
    while (start < end) {
      char temp = input[start];
      input[start] = input[end];
      input[end] = temp;
      start++;
      end--;
    }
  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
