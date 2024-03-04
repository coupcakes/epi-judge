package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    List<Integer> res = new ArrayList<Integer>(Collections.nCopies(num1.size() + num2.size(), 0));

    final int signOfRes = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    for (int i = num1.size() - 1; i >= 0; i--) {
      for (int j = num2.size() - 1; j >= 0; j--) {
        int ans = res.get(i + j + 1) + (num1.get(i) * num2.get(j));
        int nextAns = res.get(i + j) + (ans / 10);
        res.set(i + j, nextAns);
        res.set(i + j + 1, ans % 10);
      }
    }

    // remove extra 0's
    int startIndex = 0;
    while (startIndex < res.size() && res.get(startIndex) == 0) {
      startIndex++;
    }
    res = res.subList(startIndex, res.size());
    if (res.isEmpty()) {
      return Arrays.asList(0);
    }

    res.set(0, res.get(0) * signOfRes);
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
