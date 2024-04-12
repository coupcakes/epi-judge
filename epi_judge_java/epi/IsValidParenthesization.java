package epi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    Deque<Character> stack = new ArrayDeque<Character>();
    final Map<Character, Character> LOOKUP = Map.of(
        '[', ']',
        '(', ')',
        '{', '}');
    for (int i = 0; i < s.length(); i++) {
      if (LOOKUP.get(s.charAt(i)) != null) {
        stack.addFirst(s.charAt(i));
      } else if (stack.isEmpty() || LOOKUP.get(stack.removeFirst()) != s.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
