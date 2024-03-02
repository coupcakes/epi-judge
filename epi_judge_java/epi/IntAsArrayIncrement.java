package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    // return plusOneSolFromBook(A);
    int carry = 1;
    int ptr = A.size() - 1;
    while (carry != 0 && ptr >= 0) {
      int currVal = A.get(ptr);
      if (currVal < 9) {
        A.set(ptr, currVal + carry);
        return A;
      } else {
        A.set(ptr--, 0);
      }
    }
    A.set(0, 1);
    A.add(0);
    return A;
  }

  private static List<Integer> plusOneSolFromBook(List<Integer> A) {
    int ptr = A.size() - 1;
    A.set(ptr, A.get(ptr) + 1);
    for (int i = ptr; i > 0 && A.get(i) == 10; i--) {
      A.set(i, 0);
      A.set(i - 1, A.get(i - 1) + 1);
    }

    if (A.get(0) == 10) {
      A.set(0, 1);
      A.add(0);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
